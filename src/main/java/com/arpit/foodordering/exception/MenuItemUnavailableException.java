package com.arpit.foodordering.exception;

public class MenuItemUnavailableException extends RuntimeException{
	public MenuItemUnavailableException(String message) {
		super(message);
	}

}
