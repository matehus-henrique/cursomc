package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	
	public Categoria busca(Integer id) {
		 Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}

}

/*
 * 
 * public Categoria find(Integer id) {
 Categoria obj = repo.findOne(id);
if (obj == null) {
 throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
 + ", Tipo: " + Categoria.class.getName());
 }
return obj;
} */
 