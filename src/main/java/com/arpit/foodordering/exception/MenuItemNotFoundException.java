package com.arpit.foodordering.exception;

public class MenuItemNotFoundException extends RuntimeException{
	public MenuItemNotFoundException(String message) {
		super(message);
	}

}
