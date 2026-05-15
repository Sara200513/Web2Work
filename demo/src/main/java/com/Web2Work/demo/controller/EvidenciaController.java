package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Evidencia;
import com.Web2Work.demo.service.EvidenciaService;
import com.Web2Work.demo.service.ActividadService;
import com.Web2Work.demo.service.AsignacionService;
import com.Web2Work.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/evidencias")
public class EvidenciaController {

    @Autowired
    private EvidenciaService evidenciaService;

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("evidencias", evidenciaService.findAll());
        return "evidencias/lista";
    }

    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("evidencia", new Evidencia());
        model.addAttribute("actividades", actividadService.findAll());
        model.addAttribute("asignaciones", asignacionService.findAll());
        model.addAttribute("usuarios", usuarioService.findAll());
        return "evidencias/nueva";
    }

    @PostMapping("/nueva")
    public String nueva(@ModelAttribute Evidencia evidencia) {
        evidenciaService.save(evidencia);
        return "redirect:/evidencias";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {
        evidenciaService.findById(id).ifPresent(e -> model.addAttribute("evidencia", e));
        return "evidencias/detalle";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        evidenciaService.deleteById(id);
        return "redirect:/evidencias";
    }
}
