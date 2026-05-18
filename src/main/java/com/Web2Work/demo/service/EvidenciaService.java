package com.Web2Work.demo.service;

import com.Web2Work.demo.model.Evidencia;
import com.Web2Work.demo.repository.EvidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EvidenciaService {

    @Autowired
    private EvidenciaRepository evidenciaRepository;

    public List<Evidencia> findAll() {
        return evidenciaRepository.findAll();
    }

    public Optional<Evidencia> findById(Long id) {
        return evidenciaRepository.findById(id);
    }

    public List<Evidencia> findByActividadId(Long actividadId) {
        return evidenciaRepository.findByActividadId(actividadId);
    }

    public List<Evidencia> findByAsignacionId(Long asignacionId) {
        return evidenciaRepository.findByAsignacionId(asignacionId);
    }

    public List<Evidencia> findByUploadedById(Long userId) {
        return evidenciaRepository.findByUploadedById(userId);
    }

    public Evidencia save(Evidencia evidencia) {
        return evidenciaRepository.save(evidencia);
    }

    public void deleteById(Long id) {
        evidenciaRepository.deleteById(id);
    }
}
