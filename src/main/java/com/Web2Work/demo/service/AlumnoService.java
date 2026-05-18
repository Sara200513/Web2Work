package com.Web2Work.demo.service;

import com.Web2Work.demo.model.Alumno;
import com.Web2Work.demo.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> findAll() {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> findById(Long id) {
        return alumnoRepository.findById(id);
    }

    public Optional<Alumno> findByDni(String dni) {
        return alumnoRepository.findByDni(dni);
    }

    public List<Alumno> findByCurso(String curso) {
        return alumnoRepository.findByCurso(curso);
    }

    public List<Alumno> findByGrupo(String grupo) {
        return alumnoRepository.findByGrupo(grupo);
    }

    public Alumno save(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public void deleteById(Long id) {
        alumnoRepository.deleteById(id);
    }
    
    public List<Alumno> buscarPorNombre(String texto) {
        return alumnoRepository.findAll().stream()
                .filter(a -> a.getNombre().toLowerCase().contains(texto.toLowerCase()) ||
                            a.getApellidos().toLowerCase().contains(texto.toLowerCase()) ||
                            a.getDni().toLowerCase().contains(texto.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Alumno> filtrarPorCursoYGrupo(String curso, String grupo) {
        return alumnoRepository.findAll().stream()
                .filter(a -> (curso == null || curso.isEmpty() || 
                             a.getCurso().equalsIgnoreCase(curso)) &&
                            (grupo == null || grupo.isEmpty() || 
                             a.getGrupo().equalsIgnoreCase(grupo)))
                .collect(java.util.stream.Collectors.toList());
    }
}