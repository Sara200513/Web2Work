package com.Web2Work.demo.service;

import com.Web2Work.demo.model.Notificacion;
import com.Web2Work.demo.model.Usuario;
import com.Web2Work.demo.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    public Optional<Notificacion> findById(Long id) {
        return notificacionRepository.findById(id);
    }

    public List<Notificacion> findByUsuarioId(Long usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId);
    }

    public List<Notificacion> findNoLeidasByUsuarioId(Long usuarioId) {
        return notificacionRepository.findByUsuarioIdAndLeidoFalse(usuarioId);
    }

    public List<Notificacion> findByTipo(String tipo) {
        return notificacionRepository.findByTipo(tipo);
    }

    public Notificacion save(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public void deleteById(Long id) {
        notificacionRepository.deleteById(id);
    }

    // MÉTODO CENTRAL PARA CREAR NOTIFICACIONES
    public void crearNotificacion(Usuario usuario, String tipo,
                                   String texto, String link) {
        Notificacion n = new Notificacion();
        n.setUsuario(usuario);
        n.setTipo(tipo);
        n.setTexto(texto);
        n.setLink(link);
        n.setLeido(false);
        notificacionRepository.save(n);
    }
}