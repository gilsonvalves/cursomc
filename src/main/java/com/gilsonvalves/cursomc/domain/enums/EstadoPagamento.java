package com.gilsonvalves.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1,"Pendente"),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado");
	
	private int cod;
	private String descricao;
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.setDescricao(descricao);
	}
	
	public int getcod() {
		return cod;
	}
	
	public String getCod(String descricao) {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) {return null;}
		for(EstadoPagamento x: EstadoPagamento.values()) {
			if(cod.equals(x.cod)) {
				return x;
			}
		}
		throw new IllegalArgumentException("id inválido: "+cod);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
