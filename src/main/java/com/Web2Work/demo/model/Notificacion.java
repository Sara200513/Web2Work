package com.Web2Work.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Entity
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String texto;

    private String link;

    @Column(nullable = false)
    private Boolean leido = false;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
 // GETTERS
    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public String getTipo() { return tipo; }
    public String getTexto() { return texto; }
    public String getLink() { return link; }
    public Boolean getLeido() { return leido; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setTexto(String texto) { this.texto = texto; }
    public void setLink(String link) { this.link = link; }
    public void setLeido(Boolean leido) { this.leido = leido; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}