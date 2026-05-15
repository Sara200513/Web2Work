package com.Web2Work.demo.controller;

import com.Web2Work.demo.model.Profesor;
import com.Web2Work.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ConvenioFEService convenioFEService;

    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("alumnos", alumnoService.findAll());
        model.addAttribute("convenios", convenioFEService.findAll());
        return "profesores/dashboard";
    }

    @GetMapping("/alumnos")
    public String listarAlumnos(Model model) {
        model.addAttribute("alumnos", alumnoService.findAll());
        return "profesores/alumnos";
    }

    @GetMapping("/alumnos/{id}")
    public String verAlumno(@PathVariable Long id, Model model) {
        alumnoService.findById(id).ifPresent(a -> {
            model.addAttribute("alumno", a);
            model.addAttribute("asignaciones", asignacionService.findByAlumnoId(id));
            model.addAttribute("evaluaciones", evaluacionService.findByAsignacionId(id));
        });
        return "profesores/seguimiento";
    }

    @GetMapping("/convenios")
    public String listarConvenios(Model model) {
        model.addAttribute("convenios", convenioFEService.findAll());
        return "profesores/convenios";
    }

	public ProfesorService getProfesorService() {
		return profesorService;
	}

	public void setProfesorService(ProfesorService profesorService) {
		this.profesorService = profesorService;
	}
}