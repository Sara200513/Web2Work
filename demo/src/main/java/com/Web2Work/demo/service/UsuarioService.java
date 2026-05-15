package com.Web2Work.demo.service;

import com.Web2Work.demo.model.Usuario;
import com.Web2Work.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Optional<Usuario> findByDni(String dni) {
        return usuarioRepository.findByDni(dni);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public boolean existsByDni(String dni) {
        return usuarioRepository.existsByDni(dni);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<Usuario> buscarPorNombreOEmail(String texto) {
	    return usuarioRepository.findAll().stream()
	            .filter(u -> u.getNombre().toLowerCase().contains(texto.toLowerCase()) ||
	                        u.getApellidos().toLowerCase().contains(texto.toLowerCase()) ||
	                        u.getEmail().toLowerCase().contains(texto.toLowerCase()))
	            .collect(java.util.stream.Collectors.toList());
	}

	public List<Usuario> filtrarPorRol(String rol) {
	    return usuarioRepository.findAll().stream()
	            .filter(u -> u.getRol().equalsIgnoreCase(rol))
	            .collect(java.util.stream.Collectors.toList());
	}
    
    
}
