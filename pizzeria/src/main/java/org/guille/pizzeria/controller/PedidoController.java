package org.guille.pizzeria.controller;

import org.guille.pizzeria.dto.PedidoDto;
import org.guille.pizzeria.dto.ResponseStatus;
import org.guille.pizzeria.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
