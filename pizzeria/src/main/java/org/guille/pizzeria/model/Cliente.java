package org.guille.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTE")
public class Cliente extends GenericObject{
	
	@Column(name="NOMBRE", nullable=false)
	private String nombre;
	
	@Column(name="DIRECCION", nullable=false)
	private String direccion;
	
	@Column(name="TELEFONO", nullable=false, unique=true)
	private	String telefono;
	
	@Column(name="EMAIL", nullable=false, unique=true)
	private String email;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
}
