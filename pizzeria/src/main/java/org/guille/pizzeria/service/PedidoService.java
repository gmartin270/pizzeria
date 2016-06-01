package org.guille.pizzeria.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.guille.pizzeria.dao.DetallePedidoDao;
import org.guille.pizzeria.dao.IDetallePedidoDao;
import org.guille.pizzeria.dao.IGenericDao;
import org.guille.pizzeria.dao.IPedidoDao;
import org.guille.pizzeria.dao.PedidoDao;
import org.guille.pizzeria.dto.ClienteDto;
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
	
	IDetallePedidoDao detallePedidoParticular;
	IPedidoDao pedidoParticular;
	
	@PostConstruct
	public void init(){
		detallePedidoParticular = (DetallePedidoDao)detallePedidoDao;
		pedidoParticular = (PedidoDao)pedidoDao;
	}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	@Transactional(readOnly    = false, 
		       propagation = Propagation.REQUIRED, 
	           rollbackFor = Exception.class)
	public List<PedidoDto> consultarPedidos(PedidoDto pedidoDto) throws Exception{
		List<Pedido> pedidos = null;
		List<PedidoDto> pedidoDtos = null;
		PedidoDto item = null;
		
		if(pedidoDto != null){
			Pedido pedido = new Pedido();
			
			pedido.setId(pedidoDto.getId());
			
			if(pedidoDto.getCliente() != null){
				Cliente cliente = clienteDao.load(pedidoDto.getCliente().getId());
				
				if(cliente == null)
					throw new Exception("Cliente inexistente.");
				
				pedido.setCliente(cliente);
			}
			
			if(pedidoDto.getFecha() != null)
				pedido.setFecha(Date.valueOf(pedidoDto.getFecha()));
			
			if(pedidoDto.getEstado() != null && pedidoDto.getEstado().length() > 0)
				pedido.setEstado(EstadoType.valueOf(pedidoDto.getEstado()));
			
			pedidos = pedidoParticular.obtenerPedidosByCriteria(pedido);
		}else
			pedidos = pedidoParticular.getAll();
		
		for (Pedido pedido : pedidos) {
			item = new PedidoDto();
			item.setId(pedido.getId());
			
			ClienteDto clienteDto = new ClienteDto();
			clienteDto.setId(pedido.getCliente().getId());
			clienteDto.setNombre(pedido.getCliente().getNombre());
			clienteDto.setDireccion(pedido.getCliente().getDireccion());
			clienteDto.setEmail(pedido.getCliente().getEmail());
			clienteDto.setTelefono(pedido.getCliente().getTelefono());
			
			item.setCliente(clienteDto);
			item.setDelivery(pedido.isDelivery());
			item.setEstado(pedido.getEstado().toString());
			item.setFecha(sdf.format(pedido.getFecha()));
			
			if(pedidoDtos == null)
				pedidoDtos = new ArrayList<PedidoDto>();
			
			pedidoDtos.add(item);
		}
		
		return pedidoDtos;
	}
	
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
	
	@Transactional(readOnly    = false, 
			   	   propagation = Propagation.REQUIRED, 
			   	   rollbackFor = Exception.class)
	public void actualizarEstado(String id, String estadoNuevo) throws Exception{
		
		Long idPedido = new Long(id);
		
		String validacion = validarCambioEstado(idPedido, EstadoType.valueOf(estadoNuevo)); 
		
		if(validacion.length() > 0)
			throw new Exception(validacion);
		else{
			Pedido pedido = pedidoDao.load(idPedido);
			List<DetallePedido> detalle = detallePedidoParticular.getDetalleByPedido(pedido);
			
			pedido.setDetalle(detalle);
			pedido.setEstado(EstadoType.valueOf(estadoNuevo));
			
			pedidoDao.saveOrUpdate(pedido);
		}	
	}
	
	private String validarCambioEstado(Long idPedido, EstadoType estadoNuevo){
		String resultado = "";
		final String transicionIncorrecta = "No es posible realizar la transicion especificada.";
		
		Pedido pedido = pedidoDao.load(idPedido);		
		EstadoType estadoActual = pedido.getEstado();
		
		if(estadoNuevo.compareTo(estadoActual) >= 0){
			switch(estadoActual){
			case INICIAL:
				if(estadoNuevo.ordinal() > 2)
					resultado = transicionIncorrecta;
				
				break;
			case CANCELADO:
				resultado = transicionIncorrecta;
				break;
			case ENTREGAR:
				break;
			default:
				if(estadoActual.ordinal() + 1 != estadoNuevo.ordinal())
					resultado = transicionIncorrecta;				
			}
		}else{
			resultado = "No es posible pasar a un estado anterior al actual";
		}
		
		return resultado;
	}
}
