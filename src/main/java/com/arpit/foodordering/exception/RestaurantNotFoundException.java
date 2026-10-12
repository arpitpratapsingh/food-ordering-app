package com.arpit.foodordering.exception;

public class RestaurantNotFoundException extends RuntimeException{
	public RestaurantNotFoundException(String message) {
		super(message);
	}

}
