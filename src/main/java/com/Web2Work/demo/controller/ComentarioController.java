package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Comentario;
import com.Web2Work.demo.service.ComentarioService;
import com.Web2Work.demo.service.NotificacionService;
import com.Web2Work.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired private ComentarioService comentarioService;
    @Autowired private UsuarioService usuarioService;
    @Autowired private NotificacionService notificacionService;

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
    public String nuevo(@ModelAttribute Comentario comentario,
                        Authentication auth) {
        comentarioService.save(comentario);

        if (auth != null) {
            usuarioService.findByEmail(auth.getName()).ifPresent(tutor -> {
                String textoTutor = tutor.getNombre() + " " + tutor.getApellidos()
                    + " ha dejado una observación.";

                // Notificar al alumno
                if (comentario.getTargetType() != null
                        && comentario.getTargetType().equals("alumno")) {
                    usuarioService.findById(comentario.getTargetId())
                        .ifPresent(alumno -> notificacionService.crearNotificacion(
                            alumno, "COMENTARIO",
                            tutor.getNombre() + " " + tutor.getApellidos()
                                + " ha dejado una observación sobre ti.",
                            "/alumnos/progreso"));
                }

                // Notificar a todos los profesores excepto quien lo dejó
                usuarioService.findAll().stream()
                    .filter(u -> u.getRol().equals("profesor")
                              && !u.getId().equals(tutor.getId()))
                    .forEach(u -> notificacionService.crearNotificacion(
                        u, "COMENTARIO", textoTutor, "/alumnos"));

                // Notificar a todos los admins
                usuarioService.findAll().stream()
                    .filter(u -> u.getRol().equals("admin"))
                    .forEach(u -> notificacionService.crearNotificacion(
                        u, "COMENTARIO", textoTutor, "/admin/dashboard"));
            });
        }

        return "redirect:/alumnos";
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