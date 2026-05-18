package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Mensaje;
import com.Web2Work.demo.model.Usuario;
import com.Web2Work.demo.service.MensajeService;
import com.Web2Work.demo.service.NotificacionService;
import com.Web2Work.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/mensajes")
public class MensajeController {

    @Autowired private MensajeService      mensajeService;
    @Autowired private UsuarioService      usuarioService;
    @Autowired private NotificacionService notificacionService;

    @GetMapping
    public String bandeja(Model model, Authentication auth) {
        if (auth != null) {
            usuarioService.findByEmail(auth.getName()).ifPresentOrElse(u -> {
                // Conversaciones agrupadas por último mensaje
                List<Mensaje> conversaciones =
                    mensajeService.findConversacionesByUserId(u.getId());
                model.addAttribute("mensajes", conversaciones);
                model.addAttribute("usuarioActualId", u.getId());
            }, () -> model.addAttribute("mensajes", List.of()));
        } else {
            model.addAttribute("mensajes", List.of());
        }
        return vistaSegunRol(auth,
                "mensajes/bandeja",
                "mensajes/bandeja-profesor",
                "mensajes/bandeja-empresa",
                "mensajes/bandeja-admin");
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model, Authentication auth) {
        model.addAttribute("mensaje", new Mensaje());
        if (auth != null) {
            usuarioService.findByEmail(auth.getName()).ifPresentOrElse(u -> {
                var todos = usuarioService.findAll();
                todos.removeIf(us -> us.getId().equals(u.getId()));
                model.addAttribute("usuarios", todos);
            }, () -> model.addAttribute("usuarios", usuarioService.findAll()));
        } else {
            model.addAttribute("usuarios", usuarioService.findAll());
        }
        return vistaSegunRol(auth,
                "mensajes/nuevo",
                "mensajes/nuevo-profesor",
                "mensajes/nuevo-empresa",
                "mensajes/nuevo-admin");
    }

    @PostMapping("/nuevo")
    public String nuevo(@RequestParam Long toUserId,
                        @RequestParam String asunto,
                        @RequestParam String cuerpo,
                        Authentication auth) {
        if (auth == null) return "redirect:/login";

        usuarioService.findByEmail(auth.getName()).ifPresent(fromUser ->
            usuarioService.findById(toUserId).ifPresent(toUser -> {
                String conversacionId = UUID.randomUUID().toString();

                Mensaje m = new Mensaje();
                m.setFromUser(fromUser);
                m.setToUser(toUser);
                m.setAsunto(asunto);
                m.setCuerpo(cuerpo);
                m.setLeido(false);
                m.setConversacionId(conversacionId);
                mensajeService.save(m);

                notificacionService.crearNotificacion(
                    toUser, "MENSAJE",
                    fromUser.getNombre() + " " + fromUser.getApellidos()
                        + " te ha enviado un mensaje: " + asunto,
                    "/mensajes/conversacion/" + conversacionId);
            })
        );
        return "redirect:/mensajes";
    }

    @GetMapping("/conversacion/{conversacionId}")
    public String verConversacion(@PathVariable String conversacionId,
                                   Model model, Authentication auth) {
        var mensajes = mensajeService.findByConversacionId(conversacionId);

        if (auth != null) {
            usuarioService.findByEmail(auth.getName()).ifPresent(u -> {
                mensajes.forEach(m -> {
                    if (m.getToUser().getId().equals(u.getId()) && !m.getLeido()) {
                        m.setLeido(true);
                        mensajeService.save(m);
                    }
                });
                model.addAttribute("usuarioActualId", u.getId());
            });
        }

        model.addAttribute("mensajes", mensajes);
        model.addAttribute("conversacionId", conversacionId);
        model.addAttribute("asunto",
            mensajes.isEmpty() ? "Conversacion" : mensajes.get(0).getAsunto());

        return vistaSegunRol(auth,
                "mensajes/conversacion",
                "mensajes/conversacion-profesor",
                "mensajes/conversacion-empresa",
                "mensajes/conversacion-admin");
    }

    @PostMapping("/responder")
    public String responder(@RequestParam String conversacionId,
                            @RequestParam String cuerpo,
                            Authentication auth) {
        if (auth == null) return "redirect:/login";

        var mensajes = mensajeService.findByConversacionId(conversacionId);
        if (mensajes.isEmpty()) return "redirect:/mensajes";

        usuarioService.findByEmail(auth.getName()).ifPresent(fromUser -> {
            Mensaje original = mensajes.get(0);
            Usuario toUser = original.getFromUser().getId().equals(fromUser.getId())
                    ? original.getToUser()
                    : original.getFromUser();

            Mensaje respuesta = new Mensaje();
            respuesta.setFromUser(fromUser);
            respuesta.setToUser(toUser);
            respuesta.setAsunto("Re: " + original.getAsunto());
            respuesta.setCuerpo(cuerpo);
            respuesta.setLeido(false);
            respuesta.setConversacionId(conversacionId);
            mensajeService.save(respuesta);

            notificacionService.crearNotificacion(
                toUser, "MENSAJE",
                fromUser.getNombre() + " " + fromUser.getApellidos()
                    + " ha respondido a: " + original.getAsunto(),
                "/mensajes/conversacion/" + conversacionId);
        });

        return "redirect:/mensajes/conversacion/" + conversacionId;
    }

    private String vistaSegunRol(Authentication auth,
                                  String alumno, String profesor,
                                  String empresa, String admin) {
        if (auth == null) return alumno;
        for (var a : auth.getAuthorities()) {
            switch (a.getAuthority()) {
                case "ROLE_PROFESOR" -> { return profesor; }
                case "ROLE_EMPRESA"  -> { return empresa;  }
                case "ROLE_ADMIN"    -> { return admin;    }
            }
        }
        return alumno;
    }
}