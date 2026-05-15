package com.Web2Work.demo.service;

import com.Web2Work.demo.model.Empresa;
import com.Web2Work.demo.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> findById(Long id) {
        return empresaRepository.findById(id);
    }

    public Optional<Empresa> findByCif(String cif) {
        return empresaRepository.findByCif(cif);
    }

    public List<Empresa> findBySector(String sector) {
        return empresaRepository.findBySector(sector);
    }
    
    public boolean existsByCif(String cif) {
        return empresaRepository.existsByCif(cif);
    }

    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public void deleteById(Long id) {
        empresaRepository.deleteById(id);
    }
}
