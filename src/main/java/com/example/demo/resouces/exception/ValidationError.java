package com.example.demo.resouces.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	
	private List<FieldMassage> erros = new ArrayList<>();
	
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}


	public List<FieldMassage> getErros() {
		return erros;
	}


	public void addError(String fieldName, String messagem) {
		erros.add(new FieldMassage(fieldName, messagem));
	}

	

}
