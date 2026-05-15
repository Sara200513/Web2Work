package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Mensaje;
import com.Web2Work.demo.service.MensajeService;
import com.Web2Work.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String bandeja(Model model) {
        model.addAttribute("mensajes", mensajeService.findAll());
        return "mensajes/bandeja";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("mensaje", new Mensaje());
        model.addAttribute("usuarios", usuarioService.findAll());
        return "mensajes/nuevo";
    }

    @PostMapping("/nuevo")
    public String nuevo(@ModelAttribute Mensaje mensaje) {
        mensajeService.save(mensaje);
        return "redirect:/mensajes";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {
        mensajeService.findById(id).ifPresent(m -> model.addAttribute("mensaje", m));
        return "mensajes/detalle";
    }
}