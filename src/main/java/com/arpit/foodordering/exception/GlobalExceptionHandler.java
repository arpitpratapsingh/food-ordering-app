package com.arpit.foodordering.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.arpit.foodordering.dto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	//Customer not found exception
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> customerNotFoundException(CustomerNotFoundException ex){
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setMessage(ex.getMessage());
		response.setData(null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	//Duplicate Customer Exception
	@ExceptionHandler(DuplicateCustomerException.class)
	public ResponseEntity<ResponseStructure<String>> duplicateCustomerException(DuplicateCustomerException ex){
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setMessage(ex.getMessage());
		response.setData(null);

		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}


	//InvalidOrderStatusException
	@ExceptionHandler(InvalidOrderStatusException.class)
	public ResponseEntity<ResponseStructure<String>> invalidOrderStatusException(InvalidOrderStatusException ex){
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setMessage(ex.getMessage());
		response.setData(null);

		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

	
	//MenuItemUnavailableException
	@ExceptionHandler(MenuItemUnavailableException.class)
	public ResponseEntity<ResponseStructure<String>> menuItemUnavailableException(MenuItemUnavailableException ex){
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setMessage(ex.getMessage());
		response.setData(null);

		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	
	//Order not found exception
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> orderNotFoundException(OrderNotFoundException ex){
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setMessage(ex.getMessage());
		response.setData(null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	

	//Payment not found exception
	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> PaymentNotFoundException(PaymentNotFoundException ex){
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setMessage(ex.getMessage());
		response.setData(null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	
	
	//RestaurantNotFoundException
	@ExceptionHandler(RestaurantNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> restaurantNotFoundException(RestaurantNotFoundException ex){
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setMessage(ex.getMessage());
		response.setData(null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	
	//Generic exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseStructure<String>> genericException(Exception ex){
		
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setMessage("An unexpected error occurred. Please try again later.");
		response.setData(null);

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
}
