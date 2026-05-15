package com.Web2Work.demo.controller;

import com.Web2Work.demo.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("notificaciones", notificacionService.findAll());
        return "notificaciones/lista";
    }

    @GetMapping("/usuario/{id}")
    public String listarPorUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("notificaciones", notificacionService.findByUsuarioId(id));
        return "notificaciones/lista";
    }
}
