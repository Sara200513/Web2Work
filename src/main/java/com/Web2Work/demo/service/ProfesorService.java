package com.Web2Work.demo.service;

import com.Web2Work.demo.model.Profesor;
import com.Web2Work.demo.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> findAll() {
        return profesorRepository.findAll();
    }

    public Optional<Profesor> findById(Long id) {
        return profesorRepository.findById(id);
    }

    public Optional<Profesor> findByDni(String dni) {
        return profesorRepository.findByDni(dni);
    }

    public List<Profesor> findByDepartamento(String departamento) {
        return profesorRepository.findByDepartamento(departamento);
    }

    public Profesor save(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public void deleteById(Long id) {
        profesorRepository.deleteById(id);
    }
}
