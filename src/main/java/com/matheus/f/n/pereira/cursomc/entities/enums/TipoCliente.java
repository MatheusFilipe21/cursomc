package com.matheus.f.n.pereira.cursomc.entities.enums;

public enum TipoCliente {

	PESSOA_FISICA(1, "Pessoa Física"),
	PESSOA_JURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente valueOf(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (TipoCliente value : TipoCliente.values()) {
			if (cod.equals(value.getCod())) {
				return value;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
