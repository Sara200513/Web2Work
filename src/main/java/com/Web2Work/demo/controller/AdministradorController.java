package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.*;
import com.Web2Work.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired private UsuarioService       usuarioService;
    @Autowired private AdministradorService administradorService;
    @Autowired private ConvenioFEService    convenioFEService;
    @Autowired private NotificacionService  notificacionService;
    @Autowired private InformeService       informeService;
    @Autowired private AlumnoService        alumnoService;
    @Autowired private ProfesorService      profesorService;
    @Autowired private EmpresaService       empresaService;
    @Autowired private EvaluacionService    evaluacionService;
    @Autowired private PasswordEncoder      passwordEncoder;

    // ── Dashboard ─────────────────────────────────────────────────────────────
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        var usuarios  = usuarioService.findAll();
        var convenios = convenioFEService.findAll();
        model.addAttribute("usuarios",           usuarios);
        model.addAttribute("convenios",          convenios);
        model.addAttribute("totalUsuarios",      usuarios.size());
        model.addAttribute("totalConvenios",     convenios.size());
        model.addAttribute("alumnosEnPracticas", alumnoService.findAll().size());
        model.addAttribute("notificacionesHoy",  0);
        return "admin/dashboard";
    }

    // ── Listado de usuarios ────────────────────────────────────────────────────
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "admin/usuarios";
    }

    // ── Nuevo usuario: formulario ──────────────────────────────────────────────
    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuarioForm(Model model) {
        return "admin/nuevoUsuario";
    }

    // ── Nuevo usuario: guardar ─────────────────────────────────────────────────
    @PostMapping("/usuarios/nuevo")
    public String nuevoUsuario(
            @RequestParam String nombre,
            @RequestParam String apellidos,
            @RequestParam String dni,
            @RequestParam String email,
            @RequestParam String telefono,
            @RequestParam String password,
            @RequestParam String rol,
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) String grupo,
            @RequestParam(required = false) String departamento,
            @RequestParam(required = false) String especialidad,
            @RequestParam(required = false) String nombreEmpresa,
            @RequestParam(required = false) String cif,
            @RequestParam(required = false) String razonSocial,
            @RequestParam(required = false) String sector,
            @RequestParam(required = false) String direccion,
            @RequestParam(required = false) String contactoNombre,
            @RequestParam(required = false) String contactoTelefono,
            Model model) {

        if (usuarioService.existsByEmail(email)) {
            model.addAttribute("mensajeError", "El correo ya está registrado.");
            return "admin/nuevoUsuario";
        }
        if (usuarioService.existsByDni(dni)) {
            model.addAttribute("mensajeError", "El DNI ya está registrado.");
            return "admin/nuevoUsuario";
        }

        String hash = passwordEncoder.encode(password);

        switch (rol) {
            case "alumno" -> {
                Alumno a = new Alumno();
                a.setNombre(nombre); a.setApellidos(apellidos);
                a.setDni(dni); a.setEmail(email); a.setTelefono(telefono);
                a.setPasswordHash(hash); a.setRol("alumno");
                a.setCuentaActiva(true);
                if (curso != null) a.setCurso(curso);
                if (grupo != null) a.setGrupo(grupo);
                alumnoService.save(a);
            }
            case "profesor" -> {
                Profesor p = new Profesor();
                p.setNombre(nombre); p.setApellidos(apellidos);
                p.setDni(dni); p.setEmail(email); p.setTelefono(telefono);
                p.setPasswordHash(hash); p.setRol("profesor");
                p.setCuentaActiva(true);
                p.setDepartamento(departamento != null ? departamento : "");
                p.setEspecialidad(especialidad != null ? especialidad : "");
                profesorService.save(p);
            }
            case "empresa" -> {
                Empresa e = new Empresa();
                e.setNombre(nombre); e.setApellidos(apellidos);
                e.setDni(dni); e.setEmail(email); e.setTelefono(telefono);
                e.setPasswordHash(hash); e.setRol("empresa");
                e.setCuentaActiva(true);
                e.setNombreEmpresa(nombreEmpresa != null ? nombreEmpresa : nombre);
                e.setCif(cif != null ? cif : "");
                e.setRazonSocial(razonSocial != null ? razonSocial : "");
                e.setSector(sector != null ? sector : "");
                e.setDireccion(direccion != null ? direccion : "");
                e.setContactoNombre(contactoNombre != null ? contactoNombre : nombre);
                e.setContactoTelefono(contactoTelefono != null ? contactoTelefono : telefono);
                empresaService.save(e);
            }
            case "admin" -> {
                Administrador adm = new Administrador();
                adm.setNombre(nombre); adm.setApellidos(apellidos);
                adm.setDni(dni); adm.setEmail(email); adm.setTelefono(telefono);
                adm.setPasswordHash(hash); adm.setRol("admin");
                adm.setCuentaActiva(true);
                adm.setNivelAcceso("total");
                adm.setDepartamento(departamento != null ? departamento : "TI");
                administradorService.save(adm);
            }
            default -> {
                model.addAttribute("mensajeError", "Rol no válido.");
                return "admin/nuevoUsuario";
            }
        }

        return "redirect:/admin/usuarios?nuevoUsuario=true";
    }

    // ── Editar usuario ────────────────────────────────────────────────────────
    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuarioForm(@PathVariable Long id, Model model) {
        usuarioService.findById(id).ifPresent(u -> model.addAttribute("usuario", u));
        return "admin/editarUsuario";
    }

    @PostMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Long id,
                                @ModelAttribute Usuario usuario) {
        usuarioService.findById(id).ifPresent(u -> {
            u.setNombre(usuario.getNombre());
            u.setApellidos(usuario.getApellidos());
            u.setEmail(usuario.getEmail());
            u.setTelefono(usuario.getTelefono());
            u.setRol(usuario.getRol());
            u.setCuentaActiva(usuario.getCuentaActiva());
            usuarioService.save(u);
        });
        return "redirect:/admin/usuarios?editado=true";
    }

    // ── Toggle activo/inactivo ─────────────────────────────────────────────────
    @GetMapping("/usuarios/toggle/{id}")
    public String toggleUsuario(@PathVariable Long id) {
        usuarioService.findById(id).ifPresent(u -> {
            u.setCuentaActiva(!u.getCuentaActiva());
            usuarioService.save(u);
        });
        return "redirect:/admin/usuarios";
    }

    // ── Eliminar usuario ──────────────────────────────────────────────────────
    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return "redirect:/admin/usuarios";
    }

    // ── Roles ─────────────────────────────────────────────────────────────────
    @GetMapping("/roles")
    public String roles(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "admin/roles";
    }

    @PostMapping("/roles/cambiar")
    public String cambiarRol(@RequestParam Long usuarioId,
                             @RequestParam String nuevoRol) {
        usuarioService.findById(usuarioId).ifPresent(u -> {
            u.setRol(nuevoRol);
            usuarioService.save(u);
        });
        return "redirect:/admin/roles?exito=true";
    }

    // ── Informes ──────────────────────────────────────────────────────────────
    @GetMapping("/informes")
    public String informes(Model model) {
        model.addAttribute("resumen",            informeService.resumenGlobal());
        model.addAttribute("alumnosPorCurso",    informeService.alumnosPorCurso());
        model.addAttribute("conveniosPorEstado", informeService.conveniosPorEstado());
        model.addAttribute("mediaNotas",         informeService.mediaNotasPorEmpresa());
        return "admin/informes";
    }

    @GetMapping("/informes/alumnos-empresa")
    public String informeAlumnosEmpresa(Model model) {
        model.addAttribute("alumnosPorEmpresa", informeService.alumnosPorEmpresa());
        return "admin/informe-alumnos-empresa";
    }

    @GetMapping("/informes/evaluaciones")
    public String informeEvaluaciones(@RequestParam(required = false) String tipo,
                                      Model model) {
        model.addAttribute("evaluaciones", informeService.evaluacionesEmitidas(tipo));
        model.addAttribute("tipo", tipo);
        return "admin/informe-evaluaciones";
    }

    @GetMapping("/informes/progreso")
    public String informeProgreso(Model model) {
        model.addAttribute("progreso", informeService.progresoPorAlumno());
        return "admin/informe-progreso";
    }

    // ── Config ────────────────────────────────────────────────────────────────
    @GetMapping("/config")
    public String config() { return "admin/config"; }
}