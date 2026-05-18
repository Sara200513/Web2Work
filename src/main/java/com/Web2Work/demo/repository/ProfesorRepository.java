package com.Web2Work.demo.repository;

import com.Web2Work.demo.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Optional<Profesor> findByDni(String dni);
    List<Profesor> findByDepartamento(String departamento);
}
