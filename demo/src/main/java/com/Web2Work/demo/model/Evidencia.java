package com.Web2Work.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Entity
@Table(name = "evidencias")
public class Evidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "actividad_id", nullable = false)
    private Actividad actividad;

    @ManyToOne
    @JoinColumn(name = "asignacion_id", nullable = false)
    private Asignacion asignacion;

    @ManyToOne
    @JoinColumn(name = "uploaded_by", nullable = false)
    private Usuario uploadedBy;

    @Column(nullable = false)
    private String nombreArchivo;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Long tamaño;

    @Column(nullable = false)
    private LocalDateTime fechaSubida;

    @PrePersist
    protected void onCreate() {
        fechaSubida = LocalDateTime.now();
    }
    
 // GETTERS
    public Long getId() { return id; }
    public Actividad getActividad() { return actividad; }
    public Asignacion getAsignacion() { return asignacion; }
    public Usuario getUploadedBy() { return uploadedBy; }
    public String getNombreArchivo() { return nombreArchivo; }
    public String getFilePath() { return filePath; }
    public String getFileType() { return fileType; }
    public String getDescripcion() { return descripcion; }
    public Long getTamaño() { return tamaño; }
    public LocalDateTime getFechaSubida() { return fechaSubida; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setActividad(Actividad actividad) { this.actividad = actividad; }
    public void setAsignacion(Asignacion asignacion) { this.asignacion = asignacion; }
    public void setUploadedBy(Usuario uploadedBy) { this.uploadedBy = uploadedBy; }
    public void setNombreArchivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setTamaño(Long tamaño) { this.tamaño = tamaño; }
    public void setFechaSubida(LocalDateTime fechaSubida) { this.fechaSubida = fechaSubida; }
}
