package com.matheus.f.n.pereira.cursomc.services.exceptions;

public class AuthorizationException extends RuntimeException{
	private static final long serialVersionUID = -835708562588134057L;

	public AuthorizationException(String msg) {
		super(msg);
	}
}
