package com.arthur.ipede.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.arthur.ipede.services.exception.PedidoPendenteException;
import com.arthur.ipede.services.exception.QueryVaziaException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(QueryVaziaException.class)
	public ResponseEntity<StandartError> queryVazia(QueryVaziaException e, HttpServletRequest request){
		StandartError err = new StandartError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(PedidoPendenteException.class)
	public ResponseEntity<StandartError> pedidoPendente(PedidoPendenteException e, HttpServletRequest request){
		StandartError err = new StandartError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
}
