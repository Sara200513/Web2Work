package com.Web2Work.demo.repository;

import com.Web2Work.demo.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    List<Administrador> findByNivelAcceso(String nivelAcceso);
    List<Administrador> findByDepartamento(String departamento);
}
