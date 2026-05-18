package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Usuario;
import com.Web2Work.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuarios/lista";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {
        usuarioService.findById(id).ifPresent(u -> model.addAttribute("usuario", u));
        return "usuarios/detalle";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        usuarioService.findById(id).ifPresent(u -> model.addAttribute("usuario", u));
        return "usuarios/editar";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return "redirect:/usuarios";
    }
}
