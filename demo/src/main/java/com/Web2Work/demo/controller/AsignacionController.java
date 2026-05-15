package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Asignacion;
import com.Web2Work.demo.service.AsignacionService;
import com.Web2Work.demo.service.AlumnoService;
import com.Web2Work.demo.service.ConvenioFEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asignaciones")
public class AsignacionController {

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ConvenioFEService convenioFEService;

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
    public String nueva(@ModelAttribute Asignacion asignacion) {
        asignacionService.save(asignacion);
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