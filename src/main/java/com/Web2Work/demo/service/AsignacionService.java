package com.Web2Work.demo.service;

import com.Web2Work.demo.model.Asignacion;
import com.Web2Work.demo.repository.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AsignacionService {

    @Autowired
    private AsignacionRepository asignacionRepository;

    public List<Asignacion> findAll() {
        return asignacionRepository.findAll();
    }

    public Optional<Asignacion> findById(Long id) {
        return asignacionRepository.findById(id);
    }

    public List<Asignacion> findByAlumnoId(Long alumnoId) {
        return asignacionRepository.findByAlumnoId(alumnoId);
    }

    public List<Asignacion> findByConvenioId(Long convenioId) {
        return asignacionRepository.findByConvenioId(convenioId);
    }

    public List<Asignacion> findByEstadoAsignacion(String estadoAsignacion) {
        return asignacionRepository.findByEstadoAsignacion(estadoAsignacion);
    }

    public boolean existsByAlumnoIdAndConvenioId(Long alumnoId, Long convenioId) {
        return asignacionRepository.existsByAlumnoIdAndConvenioId(alumnoId, convenioId);
    }

    public Asignacion save(Asignacion asignacion) {
        return asignacionRepository.save(asignacion);
    }

    public void deleteById(Long id) {
        asignacionRepository.deleteById(id);
    }
}