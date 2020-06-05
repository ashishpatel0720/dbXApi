package com.example.dbx.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class DBXResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	//Handle all general exceptions
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex , WebRequest request){
		ExceptionResponse exceptionResponse  = new ExceptionResponse(new Date() , ex.getMessage() , request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse , HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Handle invalid exception (The exception posted was invalid)
	@ExceptionHandler(InvalidException.class)
	public final ResponseEntity<Object> handleAllInvalidException(Exception ex , WebRequest request) {
		ExceptionResponse exceptionResponse  = new ExceptionResponse(new Date() , ex.getMessage() , request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse , HttpStatus.NOT_ACCEPTABLE);
	}
	
	//Handle invalid queries in GET API
	@ExceptionHandler(InvalidQueryException.class)
	public final ResponseEntity<Object> handleAllInvalidQueryException(Exception ex , WebRequest request) {
		ExceptionResponse exceptionResponse  = new ExceptionResponse(new Date() , ex.getMessage() , request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse , HttpStatus.NOT_FOUND);
	}
	
	//Handle invalid arguments
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() , ex.getBindingResult().toString());
		
		return new ResponseEntity(exceptionResponse , HttpStatus.BAD_REQUEST);
	}
}