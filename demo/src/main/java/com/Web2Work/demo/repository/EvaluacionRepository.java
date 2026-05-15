package com.Web2Work.demo.repository;

import com.Web2Work.demo.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    List<Evaluacion> findByAsignacionId(Long asignacionId);
    List<Evaluacion> findByEvaluadorId(Long evaluadorId);
    List<Evaluacion> findByTipo(String tipo);
}