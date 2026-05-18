package com.Web2Work.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "convenios")
public class ConvenioFE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false)
    private Integer horasTotales;

    @Column(nullable = false)
    private String estado; // pendiente, activo, finalizado, cancelado

    @Column(nullable = true)
    private String documentoPath;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
 // GETTERS
    public Long getId() { return id; }
    public Empresa getEmpresa() { return empresa; }
    public Profesor getProfesor() { return profesor; }
    public String getTitulo() { return titulo; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public Integer getHorasTotales() { return horasTotales; }
    public String getEstado() { return estado; }
    public String getDocumentoPath() { return documentoPath; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public void setHorasTotales(Integer horasTotales) { this.horasTotales = horasTotales; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setDocumentoPath(String documentoPath) { this.documentoPath = documentoPath; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}