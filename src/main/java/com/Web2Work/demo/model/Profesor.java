package com.Web2Work.demo.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "profesores")
public class Profesor extends Usuario {

    @Column(nullable = false)
    private String departamento;

    @Column(nullable = false)
    private String especialidad;

    // GETTERS
    public String getDepartamento() { return departamento; }
    public String getEspecialidad() { return especialidad; }

    // SETTERS
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
}