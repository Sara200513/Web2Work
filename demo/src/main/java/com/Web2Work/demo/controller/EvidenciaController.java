package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Evidencia;
import com.Web2Work.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/evidencias")
public class EvidenciaController {

    @Autowired private EvidenciaService evidenciaService;
    @Autowired private ActividadService actividadService;
    @Autowired private AsignacionService asignacionService;
    @Autowired private UsuarioService usuarioService;

    @GetMapping("/nueva")
    public String nuevaForm(Model model, Authentication auth) {
        model.addAttribute("evidencia", new Evidencia());
        if (auth != null) {
            usuarioService.findByEmail(auth.getName()).ifPresent(u -> {
                var asigs = asignacionService.findByAlumnoId(u.getId());
                if (!asigs.isEmpty()) {
                    model.addAttribute("actividades",
                            actividadService.findByAsignacionId(asigs.get(0).getId()));
                    return;
                }
                model.addAttribute("actividades", List.of());
            });
        }
        if (!model.containsAttribute("actividades")) {
            model.addAttribute("actividades", actividadService.findAll());
        }
        return "evidencias/nueva";
    }

    @PostMapping("/nueva")
    public String nueva(
            @RequestParam Long actividadId,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) MultipartFile archivo,
            Authentication auth) {

        Evidencia evidencia = new Evidencia();
        actividadService.findById(actividadId).ifPresent(act -> {
            evidencia.setActividad(act);
            evidencia.setAsignacion(act.getAsignacion());
        });

        evidencia.setDescripcion(descripcion != null ? descripcion : "");

        if (archivo != null && !archivo.isEmpty()) {
            evidencia.setNombreArchivo(archivo.getOriginalFilename());
            evidencia.setFileType(archivo.getContentType());
            evidencia.setTamaño(archivo.getSize());
            evidencia.setFilePath("/uploads/" + archivo.getOriginalFilename());
        }

        if (auth != null) {
            usuarioService.findByEmail(auth.getName())
                    .ifPresent(evidencia::setUploadedBy);
        }

        evidenciaService.save(evidencia);
        return "redirect:/actividades/nueva?exito=true";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {
        evidenciaService.findById(id).ifPresent(e -> model.addAttribute("evidencia", e));
        return "evidencias/detalle";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        evidenciaService.deleteById(id);
        return "redirect:/actividades/nueva";
    }
}
