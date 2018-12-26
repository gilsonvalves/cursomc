package com.gilsonvalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gilsonvalves.cursomc.domain.Cidade;
import com.gilsonvalves.cursomc.domain.Cliente;
import com.gilsonvalves.cursomc.domain.Endereco;
import com.gilsonvalves.cursomc.domain.enums.TipoCliente;
import com.gilsonvalves.cursomc.domain.Cliente;
import com.gilsonvalves.cursomc.dto.ClienteDTO;
import com.gilsonvalves.cursomc.dto.ClienteNewDTO;
import com.gilsonvalves.cursomc.repositories.CidadeRepository;
import com.gilsonvalves.cursomc.repositories.ClienteRepository;
import com.gilsonvalves.cursomc.repositories.EnderecoRepository;
import com.gilsonvalves.cursomc.services.exception.ObjectNotFoundException;
import com.gilsonvalves.cursomc.services.excptions.DataIntegrityException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! "
				+ "Id:"+id+", Tipo"+Cliente.class.getName()));
	}
	
	public Cliente update(Cliente obj){
		Cliente newObj =  find(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
		
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao e possivel excluir uma categoria que possui produto!");
		}
	}
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linePerPage, String ordeBy, String direction){
		PageRequest pageResquest =  PageRequest.of(page, linePerPage,Direction.valueOf(direction),ordeBy);
		return repo.findAll(pageResquest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(),null,null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
			return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
