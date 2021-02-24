package com.matheus.f.n.pereira.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -7964808906858936390L;

	public ObjectNotFoundException(String nomeEntidade, Integer id) {
		super("Objeto não encontrado! Na nossa base de dados não existe " + nomeEntidade  + " com o Id: " + id);
	}
}
