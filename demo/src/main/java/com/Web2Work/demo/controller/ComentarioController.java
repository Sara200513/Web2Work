package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Comentario;
import com.Web2Work.demo.service.ComentarioService;
import com.Web2Work.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("comentarios", comentarioService.findAll());
        return "comentarios/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("comentario", new Comentario());
        model.addAttribute("usuarios", usuarioService.findAll());
        return "comentarios/nuevo";
    }

    @PostMapping("/nuevo")
    public String nuevo(@ModelAttribute Comentario comentario) {
        comentarioService.save(comentario);
        return "redirect:/comentarios";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {
        comentarioService.findById(id).ifPresent(c -> model.addAttribute("comentario", c));
        return "comentarios/detalle";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        comentarioService.deleteById(id);
        return "redirect:/comentarios";
    }
}
