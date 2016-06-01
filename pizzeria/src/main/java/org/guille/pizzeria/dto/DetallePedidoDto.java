package org.guille.pizzeria.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetallePedidoDto {
	
	private ProductoDto producto;
	private double precioUnitario;
	
	public ProductoDto getProducto() {
		return producto;
	}
	public void setProducto(ProductoDto producto) {
		this.producto = producto;
	}
	
	@JsonProperty(value="precio_unitario")
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	
	@JsonProperty(value="precio_unitario")
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
}
