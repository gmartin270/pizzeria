package org.guille.pizzeria.service;

import java.util.ArrayList;
import java.util.List;

import org.guille.pizzeria.dao.IGenericDao;
import org.guille.pizzeria.dto.ProductoDto;
import org.guille.pizzeria.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductoService {
	
	@Autowired
	IGenericDao<Producto, Long> productoDao;

	@Transactional(readOnly = false, 
			propagation = Propagation.REQUIRED)
	public List<ProductoDto> listaProductos() throws Exception{
		List<ProductoDto> productoDtos = null;
		ProductoDto productoDto;
		
		List<Producto> productos = productoDao.getAll();
		
		if(productos != null && productos.size() != 0){
			for (Producto producto : productos) {
				productoDto = new ProductoDto();
				
				productoDto.setDescripcion(producto.getDescripcion());
				productoDto.setPrecio(producto.getPrecio());
				
				if(productoDtos == null)
					productoDtos = new ArrayList<ProductoDto>();
				
				productoDtos.add(productoDto);
			}
		}
		
		return productoDtos;
	}
}
