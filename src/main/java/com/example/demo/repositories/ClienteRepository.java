package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
// um obj desse pode gera vairas coisas busca salva
	
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);

	
}
