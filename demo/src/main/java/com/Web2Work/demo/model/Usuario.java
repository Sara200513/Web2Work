package com.Web2Work.demo.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String apellidos;
	
	@Column(nullable = false, unique = true)
	private String dni;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String passwordHash;
	
	@Column(nullable = false)
	private String telefono;
	
	@Column(nullable = false)
	private String rol; //alumno, profesor, empresa, admin
	
	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;
	
	@Column(nullable = false)
	private Boolean cuentaActiva = true;
	
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
	public String getNombre() { return nombre; }
	public String getApellidos() { return apellidos; }
	public String getDni() { return dni; }
	public String getEmail() { return email; }
	public String getPasswordHash() { return passwordHash; }
	public String getTelefono() { return telefono; }
	public String getRol() { return rol; }
	public LocalDateTime getCreatedAt() { return createdAt; }
	public LocalDateTime getUpdatedAt() { return updatedAt; }
	public Boolean getCuentaActiva() { return cuentaActiva; }

	// SETTERS
	public void setId(Long id) { this.id = id; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setApellidos(String apellidos) { this.apellidos = apellidos; }
	public void setDni(String dni) { this.dni = dni; }
	public void setEmail(String email) { this.email = email; }
	public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
	public void setTelefono(String telefono) { this.telefono = telefono; }
	public void setRol(String rol) { this.rol = rol; }
	public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
	public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
	public void setCuentaActiva(Boolean cuentaActiva) { this.cuentaActiva = cuentaActiva; }
	
	
	
}
