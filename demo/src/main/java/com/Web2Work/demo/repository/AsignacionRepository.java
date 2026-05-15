package com.Web2Work.demo.repository;

import com.Web2Work.demo.model.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
    List<Asignacion> findByAlumnoId(Long alumnoId);
    List<Asignacion> findByConvenioId(Long convenioId);
    List<Asignacion> findByEstadoAsignacion(String estadoAsignacion);
    boolean existsByAlumnoIdAndConvenioId(Long alumnoId, Long convenioId);
}
