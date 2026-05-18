package com.Web2Work.demo.service;

import com.Web2Work.demo.model.Evaluacion;
import com.Web2Work.demo.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> findAll() {
        return evaluacionRepository.findAll();
    }

    public Optional<Evaluacion> findById(Long id) {
        return evaluacionRepository.findById(id);
    }

    public List<Evaluacion> findByAsignacionId(Long asignacionId) {
        return evaluacionRepository.findByAsignacionId(asignacionId);
    }

    public List<Evaluacion> findByEvaluadorId(Long evaluadorId) {
        return evaluacionRepository.findByEvaluadorId(evaluadorId);
    }

    public List<Evaluacion> findByTipo(String tipo) {
        return evaluacionRepository.findByTipo(tipo);
    }

    public Evaluacion save(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public void deleteById(Long id) {
        evaluacionRepository.deleteById(id);
    }
}
