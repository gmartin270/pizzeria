package org.guille.pizzeria.dao;

import java.util.List;

import org.guille.pizzeria.model.DetallePedido;
import org.guille.pizzeria.model.Pedido;

public interface IDetallePedidoDao extends IGenericDao<DetallePedido, Long> {
	
	public List<DetallePedido> getDetalleByPedido(Pedido pedido);

}
