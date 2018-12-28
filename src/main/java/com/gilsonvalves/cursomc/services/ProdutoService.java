package com.gilsonvalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gilsonvalves.cursomc.domain.Categoria;
import com.gilsonvalves.cursomc.domain.Produto;
import com.gilsonvalves.cursomc.repositories.CategoriaRepository;
import com.gilsonvalves.cursomc.repositories.ProdutoRepository;
import com.gilsonvalves.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! "
				+ "Id:"+id+", Tipo"+Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome,List<Integer>ids,Integer page, Integer linePerPage, String ordeBy, String direction){
		PageRequest pageResquest =  PageRequest.of(page, linePerPage,Direction.valueOf(direction),ordeBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		
		return repo.search(nome, categorias, pageResquest);
	}
}
