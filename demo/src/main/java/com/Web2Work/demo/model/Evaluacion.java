package com.Web2Work.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Entity
@Table(name = "evaluaciones")
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asignacion_id", nullable = false)
    private Asignacion asignacion;

    @ManyToOne
    @JoinColumn(name = "evaluador_id", nullable = false)
    private Usuario evaluador;

    @Column(nullable = false)
    private String tipo; // intermedio, final

    @Column(nullable = false)
    private Double notaFinal;

    @Column(nullable = false)
    private String comentarios;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
 // GETTERS
    public Long getId() { return id; }
    public Asignacion getAsignacion() { return asignacion; }
    public Usuario getEvaluador() { return evaluador; }
    public String getTipo() { return tipo; }
    public Double getNotaFinal() { return notaFinal; }
    public String getComentarios() { return comentarios; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setAsignacion(Asignacion asignacion) { this.asignacion = asignacion; }
    public void setEvaluador(Usuario evaluador) { this.evaluador = evaluador; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setNotaFinal(Double notaFinal) { this.notaFinal = notaFinal; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
