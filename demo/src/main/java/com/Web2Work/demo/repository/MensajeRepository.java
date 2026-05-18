package com.Web2Work.demo.repository;

import com.Web2Work.demo.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByToUserId(Long userId);
    List<Mensaje> findByFromUserId(Long userId);
    List<Mensaje> findByConversacionId(String conversacionId);
    List<Mensaje> findByToUserIdAndLeidoFalse(Long userId);

    // Último mensaje de cada conversación donde participa el usuario
    @Query("SELECT m FROM Mensaje m WHERE m.conversacionId IN " +
           "(SELECT DISTINCT m2.conversacionId FROM Mensaje m2 " +
           " WHERE m2.fromUser.id = :userId OR m2.toUser.id = :userId) " +
           "AND m.id = (SELECT MAX(m3.id) FROM Mensaje m3 " +
           "            WHERE m3.conversacionId = m.conversacionId) " +
           "ORDER BY m.createdAt DESC")
    List<Mensaje> findConversacionesByUserId(@Param("userId") Long userId);
}