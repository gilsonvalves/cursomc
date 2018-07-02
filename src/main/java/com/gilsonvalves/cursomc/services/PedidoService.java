package com.gilsonvalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gilsonvalves.cursomc.domain.Pedido;
import com.gilsonvalves.cursomc.repositories.PedidoRepository;
import com.gilsonvalves.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! "
				+ "Id:"+id+", Tipo"+Pedido.class.getName()));
	}
}
