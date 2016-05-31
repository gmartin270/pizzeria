package org.guille.pizzeria.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCTO")
public class Producto extends GenericObject{
	
	@Column(name="DESCRIPCION", nullable=false, unique=true)
	private String descripcion;
	
	@Column(name="PRECIO", nullable=false)
	private Double precio;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "componente", targetEntity=Combo.class)
	private List<Combo> componentes;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public List<Combo> getComponentes() {
		return componentes;
	}
	public void setComponentes(List<Combo> componentes) {
		this.componentes = componentes;
	}
	
}
