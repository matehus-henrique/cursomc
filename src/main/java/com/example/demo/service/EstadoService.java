package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Estado;
import com.example.demo.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	
	@Autowired
	private EstadoRepository repo;
	
	public List<Estado> findALL(){
		return repo.findAllByOrderByNome();
	}

}
