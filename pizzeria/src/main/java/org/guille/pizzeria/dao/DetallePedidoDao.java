package org.guille.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import org.guille.pizzeria.model.DetallePedido;
import org.guille.pizzeria.model.Pedido;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class DetallePedidoDao extends GenericDaoImp<DetallePedido, Long> implements IDetallePedidoDao {

	public List<DetallePedido> getDetalleByPedido(Pedido pedido){
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("pedido", pedido));
		
		List<DetallePedido> detalle = getByCriteria(criterions);
		
		return detalle;
	}
}
