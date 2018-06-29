package com.gilsonvalves.cursomc.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2,"Pessoa Juridica");

	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getcod() {
		return cod;
	}
	
	public String getCod(String descricao) {
		return descricao;
	}

	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			
			return null;
			}
		for(TipoCliente x: TipoCliente.values()) {
			if(cod.equals(x.cod)) {
				return x;
			}
		}
		throw new IllegalArgumentException("id inválido: "+cod);
	}
}
