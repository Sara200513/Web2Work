package com.Web2Work.demo.service;

import com.Web2Work.demo.model.Actividad;
import com.Web2Work.demo.repository.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    public List<Actividad> findAll() {
        return actividadRepository.findAll();
    }

    public Optional<Actividad> findById(Long id) {
        return actividadRepository.findById(id);
    }

    public List<Actividad> findByAsignacionId(Long asignacionId) {
        return actividadRepository.findByAsignacionId(asignacionId);
    }

    public List<Actividad> findByCategoria(String categoria) {
        return actividadRepository.findByCategoria(categoria);
    }

    public Actividad save(Actividad actividad) {
        return actividadRepository.save(actividad);
    }

    public void deleteById(Long id) {
        actividadRepository.deleteById(id);
    }
}
