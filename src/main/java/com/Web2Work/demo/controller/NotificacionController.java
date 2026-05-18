package com.Web2Work.demo.controller;

import com.Web2Work.demo.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public String listar(Model model, Authentication auth) {
        model.addAttribute("notificaciones", notificacionService.findAll());

        if (auth != null) {
            if (auth.getAuthorities().stream().anyMatch(
                    a -> a.getAuthority().equals("ROLE_PROFESOR"))) {
                return "notificaciones/lista-profesor";
            } else if (auth.getAuthorities().stream().anyMatch(
                    a -> a.getAuthority().equals("ROLE_EMPRESA"))) {
                return "notificaciones/lista-empresa";
            } else if (auth.getAuthorities().stream().anyMatch(
                    a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return "notificaciones/lista-admin";
            }
        }
        return "notificaciones/lista";
    }

    @GetMapping("/usuario/{id}")
    public String listarPorUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("notificaciones", notificacionService.findByUsuarioId(id));
        return "notificaciones/lista";
    }
    
    @GetMapping("/marcar-leida/{id}")
    public String marcarLeida(@PathVariable Long id,
                               Authentication auth) {
        notificacionService.findById(id).ifPresent(n -> {
            n.setLeido(true);
            notificacionService.save(n);
        });
        return "redirect:/notificaciones";
    }
}