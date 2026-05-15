package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Usuario;
import com.Web2Work.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private ConvenioFEService convenioFEService;

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("convenios", convenioFEService.findAll());
        return "admin/dashboard";
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "admin/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuarioForm(@PathVariable Long id, Model model) {
        usuarioService.findById(id).ifPresent(u -> model.addAttribute("usuario", u));
        return "admin/editarUsuario";
    }

    @PostMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Long id, @ModelAttribute Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return "redirect:/admin/usuarios";
    }
    
    @Autowired
    private InformeService informeService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private EvaluacionService evaluacionService;

    // INFORMES
    @GetMapping("/informes")
    public String informes(Model model) {
        model.addAttribute("resumen", informeService.resumenGlobal());
        model.addAttribute("alumnosPorCurso", informeService.alumnosPorCurso());
        model.addAttribute("conveniosPorEstado", informeService.conveniosPorEstado());
        model.addAttribute("mediaNotas", informeService.mediaNotasPorEmpresa());
        return "admin/informes";
    }

    @GetMapping("/informes/alumnos-empresa")
    public String informeAlumnosEmpresa(Model model) {
        model.addAttribute("alumnosPorEmpresa", informeService.alumnosPorEmpresa());
        return "admin/informe-alumnos-empresa";
    }

    @GetMapping("/informes/evaluaciones")
    public String informeEvaluaciones(
            @RequestParam(required = false) String tipo,
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

    // CONFIGURACIÓN DEL SISTEMA
    @GetMapping("/config")
    public String config(Model model) {
        return "admin/config";
    }

    // GESTIÓN DE ROLES
    @GetMapping("/roles")
    public String roles(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "admin/roles";
    }

    @PostMapping("/roles/cambiar")
    public String cambiarRol(
            @RequestParam Long usuarioId,
            @RequestParam String nuevoRol,
            Model model) {
        usuarioService.findById(usuarioId).ifPresent(u -> {
            u.setRol(nuevoRol);
            usuarioService.save(u);
        });
        model.addAttribute("mensajeExito", "Rol actualizado correctamente.");
        return "redirect:/admin/roles";
    }
}
