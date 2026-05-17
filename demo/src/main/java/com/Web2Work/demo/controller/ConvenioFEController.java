package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.ConvenioFE;
import com.Web2Work.demo.service.ConvenioFEService;
import com.Web2Work.demo.service.EmpresaService;
import com.Web2Work.demo.service.NotificacionService;
import com.Web2Work.demo.service.ProfesorService;
import com.Web2Work.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/convenios")
public class ConvenioFEController {

    @Autowired private ConvenioFEService   convenioFEService;
    @Autowired private EmpresaService      empresaService;
    @Autowired private ProfesorService     profesorService;
    @Autowired private NotificacionService notificacionService;
    @Autowired private UsuarioService      usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("convenios", convenioFEService.findAll());
        return "convenios/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("convenio",   new ConvenioFE());
        model.addAttribute("empresas",   empresaService.findAll());
        model.addAttribute("profesores", profesorService.findAll());
        return "convenios/nuevo";
    }

    @PostMapping("/nuevo")
    public String nuevo(@ModelAttribute ConvenioFE convenio, Authentication auth) {
        convenioFEService.save(convenio);

        String texto = "Nuevo convenio creado con "
            + convenio.getEmpresa().getNombreEmpresa()
            + ": " + convenio.getTitulo();

        // Notificar a la empresa del convenio
        notificacionService.crearNotificacion(
            convenio.getEmpresa(), "CONVENIO",
            "Se ha creado un nuevo convenio: " + convenio.getTitulo(),
            "/convenios/" + convenio.getId());

        // Notificar a todos los admins
        usuarioService.findAll().stream()
            .filter(u -> u.getRol().equals("admin"))
            .forEach(u -> notificacionService.crearNotificacion(
                u, "CONVENIO", texto, "/admin/dashboard"));

        // Notificar a otros profesores (excepto al creador)
        if (auth != null) {
            usuarioService.findByEmail(auth.getName()).ifPresent(creador ->
                usuarioService.findAll().stream()
                    .filter(u -> u.getRol().equals("profesor")
                              && !u.getId().equals(creador.getId()))
                    .forEach(u -> notificacionService.crearNotificacion(
                        u, "CONVENIO", texto, "/convenios"))
            );
        }

        return "redirect:/convenios";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        convenioFEService.findById(id).ifPresent(c -> model.addAttribute("convenio", c));
        model.addAttribute("empresas",   empresaService.findAll());
        model.addAttribute("profesores", profesorService.findAll());
        return "convenios/editar";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute ConvenioFE convenio) {
        convenioFEService.save(convenio);
        return "redirect:/convenios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        convenioFEService.deleteById(id);
        return "redirect:/convenios";
    }
}