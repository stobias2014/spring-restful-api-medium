package com.tobias.saul.springrestfulapimedium.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tobias.saul.springrestfulapimedium.exception.StockNotFoundException;

@RestControllerAdvice
public class StockNotFoundAdvice {
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(StockNotFoundException.class)
	@ResponseBody
	public String stockNotFoundExceptionHandler(StockNotFoundException se) {
		return se.getMessage();
	}

}
