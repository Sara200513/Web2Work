package com.Web2Work.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "actividades")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asignacion_id", nullable = false)
    private Asignacion asignacion;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Integer horas;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String tipoActividad;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
 // GETTERS
    public Long getId() { return id; }
    public Asignacion getAsignacion() { return asignacion; }
    public LocalDate getFecha() { return fecha; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public Integer getHoras() { return horas; }
    public String getCategoria() { return categoria; }
    public String getTipoActividad() { return tipoActividad; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setAsignacion(Asignacion asignacion) { this.asignacion = asignacion; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setHoras(Integer horas) { this.horas = horas; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setTipoActividad(String tipoActividad) { this.tipoActividad = tipoActividad; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}