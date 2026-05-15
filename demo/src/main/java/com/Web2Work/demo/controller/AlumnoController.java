package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Alumno;
import com.Web2Work.demo.service.AlumnoService;
import com.Web2Work.demo.service.AsignacionService;
import com.Web2Work.demo.service.ActividadService;
import com.Web2Work.demo.service.EvidenciaService;
import com.Web2Work.demo.service.EvaluacionService;
import com.Web2Work.demo.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private EvidenciaService evidenciaService;

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alumnos", alumnoService.findAll());
        return "alumnos/lista";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {
        alumnoService.findById(id).ifPresent(a -> {
            model.addAttribute("alumno", a);
            model.addAttribute("asignaciones", asignacionService.findByAlumnoId(id));
            model.addAttribute("evaluaciones", evaluacionService.findByAsignacionId(id));
            model.addAttribute("notificaciones", notificacionService.findByUsuarioId(id));
        });
        return "alumnos/detalle";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "alumnos/dashboard";
    }

    @GetMapping("/progreso/{id}")
    public String progreso(@PathVariable Long id, Model model) {
        model.addAttribute("asignaciones", asignacionService.findByAlumnoId(id));
        model.addAttribute("actividades", actividadService.findByAsignacionId(id));
        model.addAttribute("evidencias", evidenciaService.findByAsignacionId(id));
        return "alumnos/progreso";
    }
}
