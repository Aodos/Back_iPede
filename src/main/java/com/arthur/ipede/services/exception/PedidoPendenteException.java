package com.arthur.ipede.services.exception;

public class PedidoPendenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public PedidoPendenteException(String msg) {
		super(msg);
	}
	
	public PedidoPendenteException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
