package com.Web2Work.demo.controller;

import com.Web2Work.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("alumnos", alumnoService.findAll());
        return "empresas/dashboard";
    }

    @GetMapping("/alumnos")
    public String listarAlumnos(Model model) {
        model.addAttribute("alumnos", alumnoService.findAll());
        return "empresas/alumnos";
    }

    @GetMapping("/alumnos/{id}")
    public String verAlumno(@PathVariable Long id, Model model) {
        alumnoService.findById(id).ifPresent(a -> {
            model.addAttribute("alumno", a);
            model.addAttribute("asignaciones", asignacionService.findByAlumnoId(id));
            model.addAttribute("evaluaciones", evaluacionService.findByAsignacionId(id));
            model.addAttribute("comentarios", comentarioService.findByUsuarioId(id));
        });
        return "empresas/seguimiento";
    }

	public EmpresaService getEmpresaService() {
		return empresaService;
	}

	public void setEmpresaService(EmpresaService empresaService) {
		this.empresaService = empresaService;
	}
}