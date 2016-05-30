package org.guille.pizzeria.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DETALLE_COMBO")
public class Combo extends GenericObject{

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMBO_ID", nullable = false)
	private Producto combo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPONENTE_ID", nullable = false)
	private Producto componente;

	public Producto getCombo() {
		return combo;
	}

	public void setCombo(Producto combo) {
		this.combo = combo;
	}

	public Producto getComponente() {
		return componente;
	}

	public void setComponente(Producto componente) {
		this.componente = componente;
	}
}
