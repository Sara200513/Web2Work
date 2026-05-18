package com.Web2Work.demo.repository;

import com.Web2Work.demo.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByCif(String cif);
    List<Empresa> findBySector(String sector);
    boolean existsByCif(String cif);
}