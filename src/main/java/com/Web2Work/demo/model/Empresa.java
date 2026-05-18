package com.Web2Work.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "empresas")
public class Empresa extends Usuario {

    @Column(nullable = false, unique = true)
    private String cif;

    @Column(nullable = false)
    private String nombreEmpresa;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String sector;

    @Column(nullable = false)
    private String razonSocial;
    
    @Column(nullable = false)
    private String contactoNombre;

    @Column(nullable = false)
    private String contactoTelefono;
    
 // GETTERS
    public String getCif() { return cif; }
    public String getNombreEmpresa() { return nombreEmpresa; }
    public String getDireccion() { return direccion; }
    public String getSector() { return sector; }
    public String getRazonSocial() { return razonSocial; }
    public String getContactoNombre() { return contactoNombre; }
    public String getContactoTelefono() { return contactoTelefono; }

    // SETTERS
    public void setCif(String cif) { this.cif = cif; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setSector(String sector) { this.sector = sector; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public void setContactoNombre(String contactoNombre) { this.contactoNombre = contactoNombre; }
    public void setContactoTelefono(String contactoTelefono) { this.contactoTelefono = contactoTelefono; }
}
