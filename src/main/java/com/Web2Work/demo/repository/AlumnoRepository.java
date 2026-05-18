package com.Web2Work.demo.repository;

import com.Web2Work.demo.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    Optional<Alumno> findByDni(String dni);
    List<Alumno> findByCurso(String curso);
    List<Alumno> findByGrupo(String grupo);
}