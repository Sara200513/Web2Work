package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Evaluacion;
import com.Web2Work.demo.service.EvaluacionService;
import com.Web2Work.demo.service.AsignacionService;
import com.Web2Work.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("evaluaciones", evaluacionService.findAll());
        return "evaluaciones/lista";
    }

    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("evaluacion", new Evaluacion());
        model.addAttribute("asignaciones", asignacionService.findAll());
        model.addAttribute("usuarios", usuarioService.findAll());
        return "evaluaciones/nueva";
    }

    @PostMapping("/nueva")
    public String nueva(@ModelAttribute Evaluacion evaluacion) {
        evaluacionService.save(evaluacion);
        return "redirect:/evaluaciones";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {
        evaluacionService.findById(id).ifPresent(e -> model.addAttribute("evaluacion", e));
        return "evaluaciones/detalle";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        evaluacionService.findById(id).ifPresent(e -> model.addAttribute("evaluacion", e));
        model.addAttribute("asignaciones", asignacionService.findAll());
        model.addAttribute("usuarios", usuarioService.findAll());
        return "evaluaciones/editar";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute Evaluacion evaluacion) {
        evaluacionService.save(evaluacion);
        return "redirect:/evaluaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        evaluacionService.deleteById(id);
        return "redirect:/evaluaciones";
    }
    
    
}
