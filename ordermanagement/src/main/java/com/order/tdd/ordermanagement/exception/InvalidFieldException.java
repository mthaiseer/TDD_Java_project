package com.order.tdd.ordermanagement.exception;

public class InvalidFieldException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;
	
	public InvalidFieldException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "InvalidFieldException [message=" + message + "]";
	}
	
	
}

