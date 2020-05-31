package com.tobias.saul.springrestfulapimedium.exception;

public class StockNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4664531481705337720L;
	
	public StockNotFoundException(String message) {
		super(message);
	}
	

}
