package com.example.demo.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.example.demo.domain.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.resouces.exception.FieldMassage;

public class ClienteUpdateValidator implements ConstraintValidator< ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize( ClienteUpdate ann) {
	}

	@SuppressWarnings("unused")
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer urlId = Integer.parseInt(map.get("id"));
		
		
		List<FieldMassage> list = new ArrayList<>();
		
		
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null && !aux.getId().equals(urlId)) {
			list.add(new FieldMassage("email", "Email já existente"));
		}
		
		

		// inclua os testes aqui, inserindo erros na lista

		for (FieldMassage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}