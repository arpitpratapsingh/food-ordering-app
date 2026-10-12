package com.arpit.foodordering.exception;

public class InvalidOrderStatusException extends RuntimeException{
	public InvalidOrderStatusException(String message) {
		super(message);
	}

}
