package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Actividad;
import com.Web2Work.demo.service.ActividadService;
import com.Web2Work.demo.service.AsignacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/actividades")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private AsignacionService asignacionService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("actividades", actividadService.findAll());
        return "actividades/lista";
    }

    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("actividad", new Actividad());
        model.addAttribute("asignaciones", asignacionService.findAll());
        return "actividades/nueva";
    }

    @PostMapping("/nueva")
    public String nueva(@ModelAttribute Actividad actividad) {
        actividadService.save(actividad);
        return "redirect:/actividades";
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
        actividadService.save(actividad);
        return "redirect:/actividades";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        actividadService.deleteById(id);
        return "redirect:/actividades";
    }
}
