package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cliente;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	
	public Cliente busca(Integer id) {
		 Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
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
 