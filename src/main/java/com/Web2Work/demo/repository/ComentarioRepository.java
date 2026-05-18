package com.Web2Work.demo.repository;

import com.Web2Work.demo.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByTargetTypeAndTargetId(String targetType, Long targetId);
    List<Comentario> findByUsuarioId(Long usuarioId);
}