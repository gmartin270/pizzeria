package org.guille.pizzeria.controller;

import java.util.List;

import org.guille.pizzeria.dto.ProductoDto;
import org.guille.pizzeria.dto.ResponseStatus;
import org.guille.pizzeria.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ProductoController {

	@Autowired(required = true)
	private ProductoService productoService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value    = "/productos", 
			        method   = RequestMethod.GET,
			        produces = "application/json")
	public ResponseEntity<?> listaProductos() throws Exception{
		List<ProductoDto> productoDtos = null;
		
		productoDtos = productoService.listaProductos();
		
		if (productoDtos == null) {
			return new ResponseEntity(new ResponseStatus("00204",
					"No existen datos de productos"),
					HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(productoDtos, HttpStatus.OK);
		}
	}
}
