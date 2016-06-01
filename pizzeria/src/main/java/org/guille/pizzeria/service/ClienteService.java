package org.guille.pizzeria.service;

import org.guille.pizzeria.dao.IGenericDao;
import org.guille.pizzeria.dto.ClienteDto;
import org.guille.pizzeria.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteService {
	
	@Autowired
	IGenericDao<Cliente, Long> clienteDao;

	@Transactional(readOnly    = false, 
				   propagation = Propagation.REQUIRED, 
			       rollbackFor = Exception.class)
	public ClienteDto nuevoCliente(ClienteDto clienteDto) throws Exception{
		
		if(clienteDto != null){
			Cliente cliente = new Cliente();
			cliente.setNombre(clienteDto.getNombre());
			cliente.setDireccion(clienteDto.getDireccion());
			cliente.setTelefono(clienteDto.getTelefono());
			cliente.setEmail(clienteDto.getEmail());
			
			clienteDao.add(cliente);
			
			clienteDto.setId(cliente.getId());
		}
		
		return clienteDto;
	}
}
