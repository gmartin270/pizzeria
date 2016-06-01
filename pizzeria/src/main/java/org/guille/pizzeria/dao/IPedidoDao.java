package org.guille.pizzeria.dao;

import java.util.List;

import org.guille.pizzeria.model.Pedido;

public interface IPedidoDao extends IGenericDao<Pedido, Long> {

	public List<Pedido> obtenerPedidosByCriteria(Pedido pedido);
}
