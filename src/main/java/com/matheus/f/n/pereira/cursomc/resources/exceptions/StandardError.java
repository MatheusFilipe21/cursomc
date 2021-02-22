package com.matheus.f.n.pereira.cursomc.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable{
	private static final long serialVersionUID = 6827215755684866121L;
	
	private Integer status;
	private String msg;
	private Instant timeStamp;
	
	public StandardError() {
	}

	public StandardError(Integer status, String msg, Instant timeStamp) {
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Instant getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Instant timeStamp) {
		this.timeStamp = timeStamp;
	}
}
