package com.example.demo.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Cliente;
import com.example.demo.domain.enums.TipoCliente;
import com.example.demo.dto.ClienteNewDTO;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.resouces.exception.FieldMassage;
import com.example.demo.service.validation.Utils.BR;

public class ClienteInsertValidator implements ConstraintValidator< ClienteInsert, ClienteNewDTO> {
	
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize( ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMassage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getcpfOuCnpj())) {
		
		list.add(new FieldMassage("cpfOuCnpj", "CPF inválido"));
		}if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getcpfOuCnpj())) {
			list.add(new FieldMassage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
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