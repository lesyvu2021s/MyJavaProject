package com.example.demo.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage handleAllException(Exception ex , WebRequest request) {
		return new ErrorMessage(500, ex.getLocalizedMessage());
		
	}
	
	@ExceptionHandler(value = {IOException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage badRequest(ProductUserNotfoundException ex , WebRequest requesst) {
		return new ErrorMessage(400, "Yêu cầu không hợp lệ ");
	}
	
	
	
}
