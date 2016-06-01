package org.guille.pizzeria.dto;

import java.util.List;

public class PedidoDto {

	private Long id;
	private String fecha;
	private ClienteDto cliente;
	private String estado;
	private boolean delivery;
	private List<DetallePedidoDto> detalle;
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public ClienteDto getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public boolean isDelivery() {
		return delivery;
	}
	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}
	public List<DetallePedidoDto> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<DetallePedidoDto> detalle) {
		this.detalle = detalle;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
