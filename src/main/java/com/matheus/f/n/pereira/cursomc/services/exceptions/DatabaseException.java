package com.matheus.f.n.pereira.cursomc.services.exceptions;

public class DatabaseException extends RuntimeException{
	private static final long serialVersionUID = -835708562588134057L;

	public DatabaseException(String msg) {
		super(msg);
	}
}
