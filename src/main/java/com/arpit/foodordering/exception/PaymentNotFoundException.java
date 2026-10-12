package com.arpit.foodordering.exception;

public class PaymentNotFoundException extends RuntimeException{
	public PaymentNotFoundException(String message) {
		super(message);
	}

}
