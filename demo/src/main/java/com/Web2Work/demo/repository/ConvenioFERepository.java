package com.Web2Work.demo.repository;

import com.Web2Work.demo.model.ConvenioFE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConvenioFERepository extends JpaRepository<ConvenioFE, Long> {
    List<ConvenioFE> findByEstado(String estado);
    List<ConvenioFE> findByEmpresaId(Long empresaId);
    List<ConvenioFE> findByProfesorId(Long profesorId);
}
