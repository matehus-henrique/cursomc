package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cidade;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.enums.Perfil;
import com.example.demo.domain.enums.TipoCliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.ClienteNewDTO;
import com.example.demo.repositories.CidadeRepository;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.EnderecoRepository;
import com.example.demo.security.UserSS;
import com.example.demo.service.exception.AuthorizationException;
import com.example.demo.service.exception.DataIntegrityException;
import com.example.demo.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	// find = a buscar.
	public Cliente busca(Integer id) {
		UserSS user = UserService.authenticated();
		if(user==null || user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
		throw new AuthorizationException("Acesso negado");	
			
		}
		
		
		 Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	
	@org.springframework.transaction.annotation.Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEndereco());
		return obj;
	}
	
	
	public Cliente update(Cliente obj) {
		Cliente newObj =  busca(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		busca(id);
		try {
		repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("NÃO É POSSIVEL EXCLUIR 'ha entidade relacionada'");
		}
	}
	
	public List<Cliente> buscaAll(){
		return repo.findAll();
	}
	
	//paginação
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy,String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null,null);
	}

	public Cliente fromDTO(ClienteNewDTO objDto)  {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getcpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()) );
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEndereco().add(end);
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

	public CidadeRepository getCidadeRepository() {
		return cidadeRepository;
	}

	public void setCidadeRepository(CidadeRepository cidadeRepository) {
		this.cidadeRepository = cidadeRepository;
	}

	public static Cliente find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}

/*
 * 
 * public Cliente find(Integer id) {
 Cliente obj = repo.findOne(id);
if (obj == null) {
 throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
 + ", Tipo: " + Cliente.class.getName());
 }
return obj;
} */
 