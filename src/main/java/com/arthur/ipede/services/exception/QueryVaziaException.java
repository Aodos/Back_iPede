package com.arthur.ipede.services.exception;

public class QueryVaziaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public QueryVaziaException(String msg) {
		super(msg);
	}
	
	public QueryVaziaException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
