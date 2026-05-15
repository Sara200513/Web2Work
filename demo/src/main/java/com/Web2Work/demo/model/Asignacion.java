package com.Web2Work.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;


@Entity
@Table(name = "asignaciones")
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "convenio_id", nullable = false)
    private ConvenioFE convenio;

    @Column(nullable = false)
    private LocalDate fechaAsignacion;

    @Column(nullable = false)
    private String estadoAsignacion; // asignado, en_practicas, finalizado

    @Column(nullable = false)
    private Integer horasRealizadas = 0;

    @PrePersist
    protected void onCreate() {
        fechaAsignacion = LocalDate.now();
    }
    
 // GETTERS
    public Long getId() { return id; }
    public Alumno getAlumno() { return alumno; }
    public ConvenioFE getConvenio() { return convenio; }
    public LocalDate getFechaAsignacion() { return fechaAsignacion; }
    public String getEstadoAsignacion() { return estadoAsignacion; }
    public Integer getHorasRealizadas() { return horasRealizadas; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setAlumno(Alumno alumno) { this.alumno = alumno; }
    public void setConvenio(ConvenioFE convenio) { this.convenio = convenio; }
    public void setFechaAsignacion(LocalDate fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }
    public void setEstadoAsignacion(String estadoAsignacion) { this.estadoAsignacion = estadoAsignacion; }
    public void setHorasRealizadas(Integer horasRealizadas) { this.horasRealizadas = horasRealizadas; }
}