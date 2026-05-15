package com.Web2Work.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String targetType; // actividad, alumno, convenio

    @Column(nullable = false)
    private Long targetId;

    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
 // GETTERS
    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public String getTargetType() { return targetType; }
    public Long getTargetId() { return targetId; }
    public String getTexto() { return texto; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setTargetType(String targetType) { this.targetType = targetType; }
    public void setTargetId(Long targetId) { this.targetId = targetId; }
    public void setTexto(String texto) { this.texto = texto; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
