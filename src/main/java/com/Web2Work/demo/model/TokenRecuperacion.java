package com.Web2Work.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens_recuperacion")
public class TokenRecuperacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime expiracion;

    @Column(nullable = false)
    private Boolean usado = false;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // GETTERS
    public Long getId() { return id; }
    public String getToken() { return token; }
    public Usuario getUsuario() { return usuario; }
    public LocalDateTime getExpiracion() { return expiracion; }
    public Boolean getUsado() { return usado; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setToken(String token) { this.token = token; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setExpiracion(LocalDateTime expiracion) { this.expiracion = expiracion; }
    public void setUsado(Boolean usado) { this.usado = usado; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Column(nullable = false)
    private String tipo; // recuperacion, verificacion

    // GETTER Y SETTER
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}