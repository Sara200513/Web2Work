package com.Web2Work.demo.service;

import com.Web2Work.demo.model.TokenRecuperacion;
import com.Web2Work.demo.model.Usuario;
import com.Web2Work.demo.repository.TokenRecuperacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenRecuperacionService {

    @Autowired
    private TokenRecuperacionRepository tokenRepository;

    public TokenRecuperacion crearToken(Usuario usuario) {
        TokenRecuperacion token = new TokenRecuperacion();
        token.setToken(UUID.randomUUID().toString());
        token.setUsuario(usuario);
        token.setExpiracion(LocalDateTime.now().plusHours(24));
        token.setUsado(false);
        return tokenRepository.save(token);
    }

    public Optional<TokenRecuperacion> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public boolean isValido(TokenRecuperacion token) {
        return !token.getUsado() &&
               token.getExpiracion().isAfter(LocalDateTime.now());
    }

    public void marcarComoUsado(TokenRecuperacion token) {
        token.setUsado(true);
        tokenRepository.save(token);
    }
    
    public TokenRecuperacion crearTokenVerificacion(Usuario usuario) {
        TokenRecuperacion token = new TokenRecuperacion();
        token.setToken(UUID.randomUUID().toString());
        token.setUsuario(usuario);
        token.setExpiracion(LocalDateTime.now().plusHours(24));
        token.setUsado(false);
        token.setTipo("verificacion");
        return tokenRepository.save(token);
    }

    public TokenRecuperacion crearToken1(Usuario usuario) {
        TokenRecuperacion token = new TokenRecuperacion();
        token.setToken(UUID.randomUUID().toString());
        token.setUsuario(usuario);
        token.setExpiracion(LocalDateTime.now().plusHours(24));
        token.setUsado(false);
        token.setTipo("recuperacion");
        return tokenRepository.save(token);
    }
}
