package com.Web2Work.demo.repository;

import com.Web2Work.demo.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByToUserId(Long userId);
    List<Mensaje> findByFromUserId(Long userId);
    List<Mensaje> findByConversacionId(String conversacionId);
    List<Mensaje> findByToUserIdAndLeidoFalse(Long userId);
}