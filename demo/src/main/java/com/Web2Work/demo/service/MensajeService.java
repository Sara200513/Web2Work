package com.Web2Work.demo.service;

import com.Web2Work.demo.model.Mensaje;
import com.Web2Work.demo.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    public List<Mensaje> findAll() {
        return mensajeRepository.findAll();
    }

    public Optional<Mensaje> findById(Long id) {
        return mensajeRepository.findById(id);
    }

    public List<Mensaje> findByToUserId(Long userId) {
        return mensajeRepository.findByToUserId(userId);
    }

    public List<Mensaje> findByFromUserId(Long userId) {
        return mensajeRepository.findByFromUserId(userId);
    }

    public List<Mensaje> findByConversacionId(String conversacionId) {
        return mensajeRepository.findByConversacionId(conversacionId);
    }

    public List<Mensaje> findNoLeidosByUserId(Long userId) {
        return mensajeRepository.findByToUserIdAndLeidoFalse(userId);
    }

    // Conversaciones agrupadas: último mensaje de cada hilo
    public List<Mensaje> findConversacionesByUserId(Long userId) {
        return mensajeRepository.findConversacionesByUserId(userId);
    }

    public Mensaje save(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    public void deleteById(Long id) {
        mensajeRepository.deleteById(id);
    }
}