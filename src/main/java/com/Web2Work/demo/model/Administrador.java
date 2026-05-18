package com.Web2Work.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "administradores")
public class Administrador extends Usuario {

    @Column(nullable = false)
    private String nivelAcceso;

    @Column(nullable = false)
    private String departamento;
    
 // GETTERS
    public String getNivelAcceso() { return nivelAcceso; }
    public String getDepartamento() { return departamento; }

    // SETTERS
    public void setNivelAcceso(String nivelAcceso) { this.nivelAcceso = nivelAcceso; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
}
