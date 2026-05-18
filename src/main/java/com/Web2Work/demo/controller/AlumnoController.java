package com.Web2Work.demo.controller;

import com.Web2Work.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired private AlumnoService alumnoService;
    @Autowired private AsignacionService asignacionService;
    @Autowired private ActividadService actividadService;
    @Autowired private EvidenciaService evidenciaService;
    @Autowired private EvaluacionService evaluacionService;
    @Autowired private NotificacionService notificacionService;
    @Autowired private UsuarioService usuarioService;

    /** Lista de alumnos — sólo para profesor/admin/empresa */
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

    /** Dashboard del alumno logueado */
    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {
        if (auth == null) return "redirect:/login";

        usuarioService.findByEmail(auth.getName()).ifPresent(u -> {
            var asignaciones = asignacionService.findByAlumnoId(u.getId());
            model.addAttribute("asignaciones", asignaciones);

            int totalHoras = 0;
            int totalActs  = 0;
            int horasTotales = 0;

            if (!asignaciones.isEmpty()) {
                var asig = asignaciones.get(0);
                model.addAttribute("asignacion", asig);
                totalHoras   = asig.getHorasRealizadas();
                horasTotales = asig.getConvenio().getHorasTotales();

                var acts = actividadService.findByAsignacionId(asig.getId());
                totalActs = acts.size();
                model.addAttribute("ultimasActividades",
                        acts.stream().limit(5).collect(java.util.stream.Collectors.toList()));

                var evals = evaluacionService.findByAsignacionId(asig.getId());
                model.addAttribute("totalEvaluaciones", evals.size());
                model.addAttribute("totalEvidencias",
                        evidenciaService.findByAsignacionId(asig.getId()).size());
            } else {
                model.addAttribute("ultimasActividades", new ArrayList<>());
                model.addAttribute("totalEvaluaciones", 0);
                model.addAttribute("totalEvidencias", 0);
            }

            model.addAttribute("horasRegistradas", totalHoras);
            model.addAttribute("horasTotales", horasTotales);
            model.addAttribute("totalActividades", totalActs);
            model.addAttribute("porcentaje",
                    horasTotales > 0 ? (totalHoras * 100 / horasTotales) : 0);
        });

        return "alumnos/dashboard";
    }

    /** Progreso del alumno logueado */
    @GetMapping("/progreso")
    public String progreso(Model model, Authentication auth) {
        if (auth == null) return "redirect:/login";

        usuarioService.findByEmail(auth.getName()).ifPresent(u -> {
            var asignaciones = asignacionService.findByAlumnoId(u.getId());
            model.addAttribute("asignaciones", asignaciones);

            if (!asignaciones.isEmpty()) {
                var asig = asignaciones.get(0);
                model.addAttribute("actividades",
                        actividadService.findByAsignacionId(asig.getId()));
                model.addAttribute("evaluaciones",
                        evaluacionService.findByAsignacionId(asig.getId()));
            } else {
                model.addAttribute("actividades", new ArrayList<>());
                model.addAttribute("evaluaciones", new ArrayList<>());
            }
        });

        return "alumnos/progreso";
    }

    /** Progreso de un alumno concreto (para profesor/empresa) */
    @GetMapping("/progreso/{id}")
    public String progresoById(@PathVariable Long id, Model model) {
        model.addAttribute("asignaciones", asignacionService.findByAlumnoId(id));
        model.addAttribute("actividades", actividadService.findByAsignacionId(id));
        model.addAttribute("evidencias", evidenciaService.findByAsignacionId(id));
        model.addAttribute("evaluaciones", evaluacionService.findByAsignacionId(id));
        return "alumnos/progreso";
    }
}
