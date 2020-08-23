package com.order.tdd.ordermanagement.exception;

public class FieldNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String fieldName;
	
	public FieldNotValidException(String fieldName, String message) {
		super(message);
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public String toString() {
		return "FieldNotValidException [fieldName=" + fieldName + "]";
	}

}
