package com.Web2Work.demo.repository;

import com.Web2Work.demo.model.Evidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EvidenciaRepository extends JpaRepository<Evidencia, Long> {
    List<Evidencia> findByActividadId(Long actividadId);
    List<Evidencia> findByAsignacionId(Long asignacionId);
    List<Evidencia> findByUploadedById(Long userId);
}