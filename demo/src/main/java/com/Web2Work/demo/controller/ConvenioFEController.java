package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.ConvenioFE;
import com.Web2Work.demo.service.ConvenioFEService;
import com.Web2Work.demo.service.EmpresaService;
import com.Web2Work.demo.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/convenios")
public class ConvenioFEController {

    @Autowired
    private ConvenioFEService convenioFEService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("convenios", convenioFEService.findAll());
        return "convenios/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("convenio", new ConvenioFE());
        model.addAttribute("empresas", empresaService.findAll());
        model.addAttribute("profesores", profesorService.findAll());
        return "convenios/nuevo";
    }

    @PostMapping("/nuevo")
    public String nuevo(@ModelAttribute ConvenioFE convenio) {
        convenioFEService.save(convenio);
        return "redirect:/convenios";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        convenioFEService.findById(id).ifPresent(c -> model.addAttribute("convenio", c));
        model.addAttribute("empresas", empresaService.findAll());
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