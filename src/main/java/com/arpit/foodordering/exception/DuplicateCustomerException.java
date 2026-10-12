package com.arpit.foodordering.exception;

public class DuplicateCustomerException extends RuntimeException{
	public DuplicateCustomerException(String message) {
		super(message);
	}

}
