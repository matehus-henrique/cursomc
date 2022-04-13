package com.example.demo.resouces.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	
	private List<FieldMassage> erros = new ArrayList<>();
	
	
	


	public ValidationError(Long temestamp, Integer status, String error, String message, String path)
			{
		super(temestamp, status, error, message, path);
		
	}


	public List<FieldMassage> getErros() {
		return erros;
	}


	public void addError(String fieldName, String messagem) {
		erros.add(new FieldMassage(fieldName, messagem));
	}

	

}
