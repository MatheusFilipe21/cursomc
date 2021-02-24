package com.matheus.f.n.pereira.cursomc.dto;

import java.io.Serializable;

import com.matheus.f.n.pereira.cursomc.entities.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = -4801333932343213182L;

	private Integer id;
	private String nome;

	public CategoriaDTO() {
	}
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
