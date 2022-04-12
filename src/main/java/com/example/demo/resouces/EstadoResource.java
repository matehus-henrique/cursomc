package com.example.demo.resouces;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Cidade;
import com.example.demo.domain.Estado;
import com.example.demo.dto.CidadeDTO;
import com.example.demo.dto.EstadoDTO;
import com.example.demo.service.CidadeService;
import com.example.demo.service.EstadoService;


@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findALL(){
		List<Estado> list = service.findALL();
		List<EstadoDTO> listDTO = list.stream().map(obj -> new EstadoDTO(obj))
		.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
		
	}
	@RequestMapping(value="/{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId){
		List<Cidade> list = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listDto = list.stream()
				.map( obj -> new CidadeDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	} 
	

}
