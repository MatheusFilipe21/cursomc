package com.matheus.f.n.pereira.cursomc.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 2460864777572417193L;

	private List<FieldMessage> errors = new ArrayList<>();;
	
	public ValidationError(Integer status, String msg, Instant timeStamp) {
		super(status, msg, timeStamp);
		
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
	
	
}
