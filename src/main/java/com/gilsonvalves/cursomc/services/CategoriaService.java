package com.gilsonvalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gilsonvalves.cursomc.domain.Categoria;
import com.gilsonvalves.cursomc.repositories.CategoriaRepository;
import com.gilsonvalves.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! "
				+ "Id:"+id+", Tipo"+Categoria.class.getName()));
	}
}
