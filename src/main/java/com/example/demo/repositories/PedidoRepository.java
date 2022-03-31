package com.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Cliente;
import com.example.demo.domain.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
// um obj desse pode gera vairas coisas busca salva
	
	@Transactional(readOnly = true)
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequrst );
	
}
