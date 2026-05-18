package com.Web2Work.demo.service;

import com.Web2Work.demo.model.ConvenioFE;
import com.Web2Work.demo.repository.ConvenioFERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConvenioFEService {

    @Autowired
    private ConvenioFERepository convenioFERepository;

    public List<ConvenioFE> findAll() {
        return convenioFERepository.findAll();
    }

    public Optional<ConvenioFE> findById(Long id) {
        return convenioFERepository.findById(id);
    }

    public List<ConvenioFE> findByEstado(String estado) {
        return convenioFERepository.findByEstado(estado);
    }

    public List<ConvenioFE> findByEmpresaId(Long empresaId) {
        return convenioFERepository.findByEmpresaId(empresaId);
    }

    public List<ConvenioFE> findByProfesorId(Long profesorId) {
        return convenioFERepository.findByProfesorId(profesorId);
    }

    public ConvenioFE save(ConvenioFE convenioFE) {
        return convenioFERepository.save(convenioFE);
    }

    public void deleteById(Long id) {
        convenioFERepository.deleteById(id);
    }
    
    public List<ConvenioFE> buscarPorTituloOEmpresa(String texto) {
        return convenioFERepository.findAll().stream()
                .filter(c -> c.getTitulo().toLowerCase().contains(texto.toLowerCase()) ||
                            c.getEmpresa().getNombreEmpresa().toLowerCase()
                             .contains(texto.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
    }

    public List<ConvenioFE> filtrarPorEstadoYEmpresa(String estado, Long empresaId) {
        return convenioFERepository.findAll().stream()
                .filter(c -> (estado == null || estado.isEmpty() || 
                             c.getEstado().equalsIgnoreCase(estado)) &&
                            (empresaId == null || 
                             c.getEmpresa().getId().equals(empresaId)))
                .collect(java.util.stream.Collectors.toList());
    }
}