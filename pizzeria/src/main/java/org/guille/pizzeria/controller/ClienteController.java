package org.guille.pizzeria.controller;

import org.guille.pizzeria.dto.ClienteDto;
import org.guille.pizzeria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClienteController {

	@Autowired(required = true)
	private ClienteService clienteService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cliente/nuevo", 
			method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> nuevoCliente(@RequestBody ClienteDto clienteDto) throws Exception{
		
		clienteDto = clienteService.nuevoCliente(clienteDto);
		
		return new ResponseEntity(clienteDto, HttpStatus.CREATED);
	}
}
