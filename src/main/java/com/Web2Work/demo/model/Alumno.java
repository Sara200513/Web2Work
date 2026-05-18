package com.Web2Work.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "alumnos")
public class Alumno extends Usuario {

    @Column(nullable = false)
    private String curso;

    @Column(nullable = false)
    private String grupo;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;
    
 // GETTERS
    public String getCurso() { return curso; }
    public String getGrupo() { return grupo; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    // SETTERS
    public void setCurso(String curso) { this.curso = curso; }
    public void setGrupo(String grupo) { this.grupo = grupo; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
}