package com.Web2Work.demo.repository;

import com.Web2Work.demo.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {
    List<Actividad> findByAsignacionId(Long asignacionId);
    List<Actividad> findByCategoria(String categoria);
}