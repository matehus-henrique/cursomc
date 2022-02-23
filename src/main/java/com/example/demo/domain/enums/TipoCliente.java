package com.example.demo.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Fisica"),
	 PESSAOJURIDICA(2, "Pessoa Juridica");

	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao ) {
		
		this.cod = cod;
		this.descricao= descricao;
		
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	public static TipoCliente toEnum(Integer cod) throws IllegalAccessException {
		if (cod == null) {
			return null;
		}
		
		for(TipoCliente x : TipoCliente.values()) {
		if(cod.equals(x.getCod())) {
			return x;
		}	
		}
		
		throw new IllegalAccessException("Id Invãlido " + cod);
	}
	
	
}
