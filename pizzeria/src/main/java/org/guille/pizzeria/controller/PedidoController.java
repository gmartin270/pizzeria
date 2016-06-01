package org.guille.pizzeria.controller;

import java.util.List;

import org.guille.pizzeria.dto.ClienteDto;
import org.guille.pizzeria.dto.PedidoDto;
import org.guille.pizzeria.dto.ResponseStatus;
import org.guille.pizzeria.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PedidoController {
	
	@Autowired(required = true)
	private PedidoService pedidoService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value    = "/pedido/registrar", 
					method   = RequestMethod.POST,
					produces = "application/json")
	public ResponseEntity<?> registrarPedido(@RequestBody PedidoDto pedidoDto) throws Exception{
		
		pedidoDto = pedidoService.registrarPedido(pedidoDto);
		
		if (pedidoDto == null) {
			return new ResponseEntity(new ResponseStatus("00409",
					"No se ha podido registrar el pedido."),
					HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity(pedidoDto, HttpStatus.CREATED);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value    = "/pedido/cambioEstado/{id}/{estado}", 
					method   = RequestMethod.PUT,
					produces = "application/json")
	public ResponseEntity<?> actualizarPedido(@PathVariable String id, @PathVariable String estado) throws Exception{
		
		try{
			pedidoService.actualizarEstado(id, estado);
			return new ResponseEntity(new ResponseStatus("00200", "Estado de pedido actualizado."), HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity(new ResponseStatus("00409",
									  e.getMessage()),
									  HttpStatus.CONFLICT);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value    = "/pedido/consulta", 
					method   = RequestMethod.GET, 
					produces = "application/json")
	public ResponseEntity<?> consultarPedidos(@RequestParam(name = "id", 
															required = false) Long id,
											  @RequestParam(name = "idCliente",
													  	    required = false) Long idCliente) throws Exception{
		
		List<PedidoDto> pedidoDtos = null;
		PedidoDto pedidoDto = new PedidoDto();
		pedidoDto.setId(id);
		
		if(idCliente != null){
			ClienteDto cliente = new ClienteDto();
			cliente.setId(idCliente);
			pedidoDto.setCliente(cliente);
		}
		
		try{
			pedidoDtos = pedidoService.consultarPedidos(pedidoDto);
		
			return new ResponseEntity(pedidoDtos, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity(new ResponseStatus("00404",
					  e.getMessage()),
					  HttpStatus.NOT_FOUND);
		}
	}
}
