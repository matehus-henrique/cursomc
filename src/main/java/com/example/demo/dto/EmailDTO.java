package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO  implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@NotEmpty(message= "Preenchimento obrigatório")
	@Email(message= "Email invalido")
	private String email;
	
	public EmailDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
