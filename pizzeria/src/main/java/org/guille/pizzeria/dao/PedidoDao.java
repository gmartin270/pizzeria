package org.guille.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import org.guille.pizzeria.model.Pedido;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoDao extends GenericDaoImp<Pedido, Long> implements IPedidoDao {

	public List<Pedido> obtenerPedidosByCriteria(Pedido pedido){
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		if(pedido.getId() != null)
			criterions.add(Restrictions.eq("id", pedido.getId()));
		
		if(pedido.getCliente() != null)
			criterions.add(Restrictions.eq("cliente", pedido.getCliente()));
		
		if(pedido.getEstado() != null)
			criterions.add(Restrictions.eq("estado", pedido.getEstado()));
		
		if(pedido.getFecha() != null)
			criterions.add(Restrictions.eq("fecha", pedido.getFecha()));
		
		List<Pedido> pedidos = this.getByCriteria(criterions);
		
		return pedidos;
	}
}
