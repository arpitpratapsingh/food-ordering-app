package com.arpit.foodordering.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Order {

	@Id
	@GeneratedValue(GenerationType.IDENTITY)
	private Long id;
	
	private String orderDateTime;
	private Status status;
	private Double totalAmount;
	
	
}
