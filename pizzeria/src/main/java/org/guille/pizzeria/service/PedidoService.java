package org.guille.pizzeria.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.guille.pizzeria.dao.IGenericDao;
import org.guille.pizzeria.dto.DetallePedidoDto;
import org.guille.pizzeria.dto.PedidoDto;
import org.guille.pizzeria.model.Cliente;
import org.guille.pizzeria.model.DetallePedido;
import org.guille.pizzeria.model.EstadoType;
import org.guille.pizzeria.model.Pedido;
import org.guille.pizzeria.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PedidoService {

	@Autowired
	IGenericDao<Pedido, Long> pedidoDao;
	
	@Autowired
	IGenericDao<Cliente, Long> clienteDao;
	
	@Autowired
	IGenericDao<DetallePedido, Long> detallePedidoDao;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	@Transactional(readOnly    = false, 
				   propagation = Propagation.REQUIRED, 
				   rollbackFor = Exception.class)
	public PedidoDto registrarPedido(PedidoDto pedidoDto) throws Exception{
		
		if(pedidoDto != null){
			Pedido pedido = null;
			DetallePedido item = null;
			Cliente cliente = null;
			Producto producto = null;
			List<DetallePedido> detalle = null;
			List<DetallePedidoDto> detalleDto = pedidoDto.getDetalle();			
			
			for (DetallePedidoDto detallePedidoDto : detalleDto) {
				item = new DetallePedido();
				
				producto = new Producto();
				producto.setId(detallePedidoDto.getProducto().getId());
				item.setProducto(producto);
				item.setPrecioUnitario(detallePedidoDto.getPrecioUnitario());
				
				if(detalle == null)
					detalle = new ArrayList<DetallePedido>();
				
				detalle.add(item);
			}
			
			cliente = clienteDao.load(pedidoDto.getCliente().getId());
			
			pedido = new Pedido();
			pedido.setCliente(cliente);
			pedido.setDelivery(pedidoDto.isDelivery());
			pedido.setFecha(GregorianCalendar.getInstance().getTime());
			pedido.setEstado(EstadoType.INICIAL);
			pedido.setDetalle(detalle);
			
			pedidoDao.saveOrUpdate(pedido);
			
			pedidoDto.setFecha(sdf.format(pedido.getFecha()));
			pedidoDto.setEstado(pedido.getEstado().toString());
			pedidoDto.setId(pedido.getId());
		}
		
		return pedidoDto;
	}
}
