package com.Web2Work.demo.controller;

import com.Web2Work.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/busqueda")
public class BusquedaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ConvenioFEService convenioFEService;

    @Autowired
    private EmpresaService empresaService;

    // BÚSQUEDA GENERAL
    @GetMapping
    public String busqueda(
            @RequestParam(required = false) String texto,
            @RequestParam(required = false) String tipo,
            Model model) {

        model.addAttribute("texto", texto);
        model.addAttribute("tipo", tipo);

        if (texto != null && !texto.isEmpty()) {
            if (tipo == null || tipo.equals("usuarios")) {
                model.addAttribute("usuarios",
                    usuarioService.buscarPorNombreOEmail(texto));
            }
            if (tipo == null || tipo.equals("alumnos")) {
                model.addAttribute("alumnos",
                    alumnoService.buscarPorNombre(texto));
            }
            if (tipo == null || tipo.equals("convenios")) {
                model.addAttribute("convenios",
                    convenioFEService.buscarPorTituloOEmpresa(texto));
            }
        }

        model.addAttribute("empresas", empresaService.findAll());
        return "busqueda/resultados";
    }

    // FILTRADO AVANZADO ALUMNOS
    @GetMapping("/alumnos")
    public String filtrarAlumnos(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) String grupo,
            @RequestParam(required = false) String texto,
            Model model) {

        if (texto != null && !texto.isEmpty()) {
            model.addAttribute("alumnos",
                alumnoService.buscarPorNombre(texto));
        } else {
            model.addAttribute("alumnos",
                alumnoService.filtrarPorCursoYGrupo(curso, grupo));
        }

        model.addAttribute("curso", curso);
        model.addAttribute("grupo", grupo);
        model.addAttribute("texto", texto);
        return "busqueda/alumnos";
    }

    // FILTRADO AVANZADO CONVENIOS
    @GetMapping("/convenios")
    public String filtrarConvenios(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Long empresaId,
            @RequestParam(required = false) String texto,
            Model model) {

        if (texto != null && !texto.isEmpty()) {
            model.addAttribute("convenios",
                convenioFEService.buscarPorTituloOEmpresa(texto));
        } else {
            model.addAttribute("convenios",
                convenioFEService.filtrarPorEstadoYEmpresa(estado, empresaId));
        }

        model.addAttribute("empresas", empresaService.findAll());
        model.addAttribute("estado", estado);
        model.addAttribute("empresaId", empresaId);
        model.addAttribute("texto", texto);
        return "busqueda/convenios";
    }
}
