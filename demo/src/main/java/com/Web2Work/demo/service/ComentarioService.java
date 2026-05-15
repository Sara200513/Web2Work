package com.Web2Work.demo.service;

import com.Web2Work.demo.model.Comentario;
import com.Web2Work.demo.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> findAll() {
        return comentarioRepository.findAll();
    }

    public Optional<Comentario> findById(Long id) {
        return comentarioRepository.findById(id);
    }

    public List<Comentario> findByTargetTypeAndTargetId(String targetType, Long targetId) {
        return comentarioRepository.findByTargetTypeAndTargetId(targetType, targetId);
    }

    public List<Comentario> findByUsuarioId(Long usuarioId) {
        return comentarioRepository.findByUsuarioId(usuarioId);
    }

    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public void deleteById(Long id) {
        comentarioRepository.deleteById(id);
    }
}