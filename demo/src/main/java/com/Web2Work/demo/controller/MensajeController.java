package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Mensaje;
import com.Web2Work.demo.service.MensajeService;
import com.Web2Work.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    public String bandeja(Model model, Authentication auth) {
        // Mostrar sólo mensajes del usuario logueado (enviados y recibidos)
        if (auth != null) {
            usuarioService.findByEmail(auth.getName()).ifPresent(u -> {
                var recibidos = mensajeService.findByToUserId(u.getId());
                var enviados  = mensajeService.findByFromUserId(u.getId());
                model.addAttribute("mensajes", recibidos);
                model.addAttribute("enviados", enviados);

                if (auth.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_PROFESOR"))) {
                    return; // se aplica el return de la lambda, la vista se elige abajo
                }
            });
        }

        if (auth != null) {
            if (auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_PROFESOR"))) {
                return "mensajes/bandeja-profesor";
            } else if (auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_EMPRESA"))) {
                return "mensajes/bandeja-empresa";
            } else if (auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return "mensajes/bandeja-admin";
            }
        }
        return "mensajes/bandeja"; // alumno
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model, Authentication auth) {
        model.addAttribute("mensaje", new Mensaje());
        // No mostrar el propio usuario como destinatario
        if (auth != null) {
            usuarioService.findByEmail(auth.getName()).ifPresent(u -> {
                var todos = usuarioService.findAll();
                todos.removeIf(us -> us.getId().equals(u.getId()));
                model.addAttribute("usuarios", todos);
            });
        } else {
            model.addAttribute("usuarios", usuarioService.findAll());
        }

        // Devolver vista según rol
        if (auth != null) {
            if (auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_PROFESOR"))) {
                return "mensajes/nuevo-profesor";
            } else if (auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_EMPRESA"))) {
                return "mensajes/nuevo-empresa";
            } else if (auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return "mensajes/nuevo-admin";
            }
        }
        return "mensajes/nuevo";
    }

    @PostMapping("/nuevo")
    public String nuevo(
            @RequestParam Long toUserId,
            @RequestParam String asunto,
            @RequestParam String cuerpo,
            Authentication auth) {

        if (auth == null) return "redirect:/login";

        usuarioService.findByEmail(auth.getName()).ifPresent(fromUser -> {
            usuarioService.findById(toUserId).ifPresent(toUser -> {
                Mensaje mensaje = new Mensaje();
                mensaje.setFromUser(fromUser);
                mensaje.setToUser(toUser);
                mensaje.setAsunto(asunto);
                mensaje.setCuerpo(cuerpo);
                mensaje.setLeido(false);
                mensajeService.save(mensaje);
            });
        });

        return "redirect:/mensajes";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model, Authentication auth) {
        mensajeService.findById(id).ifPresent(m -> {
            // Marcar como leído si el destinatario es quien accede
            if (auth != null && m.getToUser().getEmail().equals(auth.getName())) {
                m.setLeido(true);
                mensajeService.save(m);
            }
            model.addAttribute("mensaje", m);
        });

        // Elegir vista por rol para que el sidebar sea correcto
        if (auth != null) {
            if (auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_PROFESOR"))) {
                return "mensajes/detalle-profesor";
            } else if (auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_EMPRESA"))) {
                return "mensajes/detalle-empresa";
            } else if (auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return "mensajes/detalle-admin";
            }
        }
        return "mensajes/detalle";
    }
}
