package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Actividad;
import com.Web2Work.demo.model.Asignacion;
import com.Web2Work.demo.service.ActividadService;
import com.Web2Work.demo.service.AsignacionService;
import com.Web2Work.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/actividades")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model, Authentication auth) {
        if (auth != null) {
            usuarioService.findByEmail(auth.getName()).ifPresent(u -> {
                // El alumno sólo ve sus actividades; otros roles ven todas
                boolean esAlumno = auth.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_ALUMNO"));
                if (esAlumno) {
                    var asigs = asignacionService.findByAlumnoId(u.getId());
                    if (!asigs.isEmpty()) {
                        model.addAttribute("actividades",
                                actividadService.findByAsignacionId(asigs.get(0).getId()));
                    } else {
                        model.addAttribute("actividades", List.of());
                    }
                } else {
                    model.addAttribute("actividades", actividadService.findAll());
                }
            });
        } else {
            model.addAttribute("actividades", actividadService.findAll());
        }
        return "actividades/lista";
    }

    /** Formulario "Mis prácticas" del alumno: muestra form + lista de actividades */
    @GetMapping("/nueva")
    public String nuevaForm(Model model, Authentication auth) {
        model.addAttribute("actividad", new Actividad());
        if (auth != null) {
            usuarioService.findByEmail(auth.getName()).ifPresent(u -> {
                var asigs = asignacionService.findByAlumnoId(u.getId());
                model.addAttribute("asignaciones", asigs);
                if (!asigs.isEmpty()) {
                    // Pre-cargar actividades de la asignación actual para la tabla
                    model.addAttribute("actividades",
                            actividadService.findByAsignacionId(asigs.get(0).getId()));
                    model.addAttribute("asignacionActual", asigs.get(0));
                } else {
                    model.addAttribute("actividades", List.of());
                }
            });
        }
        return "actividades/nueva";
    }

    /** Guardar nueva actividad con asignacion correctamente enlazada */
    @PostMapping("/nueva")
    public String nueva(
            @RequestParam String titulo,
            @RequestParam String descripcion,
            @RequestParam Integer horas,
            @RequestParam String categoria,
            @RequestParam String fecha,
            @RequestParam(required = false) Long asignacionId,
            Authentication auth,
            Model model) {

        Actividad actividad = new Actividad();
        actividad.setTitulo(titulo);
        actividad.setDescripcion(descripcion);
        actividad.setHoras(horas);
        actividad.setCategoria(categoria);
        actividad.setFecha(LocalDate.parse(fecha));

        // Buscar asignación: primero por parámetro, luego por el alumno logueado
        if (asignacionId != null) {
            asignacionService.findById(asignacionId)
                    .ifPresent(actividad::setAsignacion);
        } else if (auth != null) {
            usuarioService.findByEmail(auth.getName()).ifPresent(u -> {
                var asigs = asignacionService.findByAlumnoId(u.getId());
                if (!asigs.isEmpty()) {
                    actividad.setAsignacion(asigs.get(0));
                    // Actualizar horas realizadas en la asignación
                    Asignacion asig = asigs.get(0);
                    asig.setHorasRealizadas(asig.getHorasRealizadas() + horas);
                    asignacionService.save(asig);
                }
            });
        }

        actividadService.save(actividad);
        model.addAttribute("mensajeExito", "Actividad registrada correctamente.");
        return "redirect:/actividades/nueva?exito=true";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {
        actividadService.findById(id).ifPresent(a -> model.addAttribute("actividad", a));
        return "actividades/detalle";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        actividadService.findById(id).ifPresent(a -> model.addAttribute("actividad", a));
        model.addAttribute("asignaciones", asignacionService.findAll());
        return "actividades/editar";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute Actividad actividad) {
        actividad.setId(id);
        actividadService.save(actividad);
        return "redirect:/actividades";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        actividadService.deleteById(id);
        return "redirect:/actividades";
    }
}
