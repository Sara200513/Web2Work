package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Asignacion;
import com.Web2Work.demo.service.AsignacionService;
import com.Web2Work.demo.service.AlumnoService;
import com.Web2Work.demo.service.ConvenioFEService;
import com.Web2Work.demo.service.NotificacionService;
import com.Web2Work.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asignaciones")
public class AsignacionController {

    @Autowired private AsignacionService asignacionService;
    @Autowired private AlumnoService alumnoService;
    @Autowired private ConvenioFEService convenioFEService;
    @Autowired private NotificacionService notificacionService;
    @Autowired private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("asignaciones", asignacionService.findAll());
        return "asignaciones/lista";
    }

    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("asignacion", new Asignacion());
        model.addAttribute("alumnos", alumnoService.findAll());
        model.addAttribute("convenios", convenioFEService.findAll());
        return "asignaciones/nueva";
    }

    @PostMapping("/nueva")
    public String nueva(@ModelAttribute Asignacion asignacion,
                        Authentication auth) {
        asignacionService.save(asignacion);

        var alumno = asignacion.getAlumno();
        var empresa = asignacion.getConvenio().getEmpresa();
        String textoAdmin = "Nueva asignación: " + alumno.getNombre() + " "
            + alumno.getApellidos() + " → " + empresa.getNombreEmpresa();

        // Notificar al alumno
        notificacionService.crearNotificacion(
            alumno, "ASIGNACION",
            "Has sido asignado a la empresa: " + empresa.getNombreEmpresa(),
            "/alumnos/dashboard");

        // Notificar a la empresa
        notificacionService.crearNotificacion(
            empresa, "ASIGNACION",
            "Se ha asignado un nuevo alumno: "
                + alumno.getNombre() + " " + alumno.getApellidos(),
            "/empresas/dashboard");

        // Notificar a todos los profesores
        usuarioService.findAll().stream()
            .filter(u -> u.getRol().equals("profesor"))
            .forEach(u -> notificacionService.crearNotificacion(
                u, "ASIGNACION", textoAdmin, "/profesores/alumnos"));

        // Notificar a todos los admins
        usuarioService.findAll().stream()
            .filter(u -> u.getRol().equals("admin"))
            .forEach(u -> notificacionService.crearNotificacion(
                u, "ASIGNACION", textoAdmin, "/admin/dashboard"));

        return "redirect:/asignaciones";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {
        asignacionService.findById(id).ifPresent(a -> model.addAttribute("asignacion", a));
        return "asignaciones/detalle";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        asignacionService.findById(id).ifPresent(a -> model.addAttribute("asignacion", a));
        model.addAttribute("alumnos", alumnoService.findAll());
        model.addAttribute("convenios", convenioFEService.findAll());
        return "asignaciones/editar";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute Asignacion asignacion) {
        asignacionService.save(asignacion);
        return "redirect:/asignaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        asignacionService.deleteById(id);
        return "redirect:/asignaciones";
    }
}