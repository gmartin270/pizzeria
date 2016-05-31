package org.guille.pizzeria.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PEDIDO")
public class Pedido extends GenericObject {

	@Column(name="FECHA", nullable=false)
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTE_ID", nullable = false)
	private Cliente cliente;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="ESTADO_TYPE", nullable=false)
	private EstadoType estado;
	
	@Column(name="DELIVERY", nullable=false)
	private boolean delivery;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", targetEntity=DetallePedido.class)
	private List<DetallePedido> detalle;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EstadoType getEstado() {
		return estado;
	}

	public void setEstado(EstadoType estado) {
		this.estado = estado;
	}

	public boolean isDelivery() {
		return delivery;
	}

	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}

	public List<DetallePedido> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetallePedido> detalle) {
		this.detalle = detalle;
	}
}
