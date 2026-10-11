package com.arpit.foodordering.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Payment paymentMethod;
	private PaymentStatus paymentStatus;
	private Double paymentAmount;
	
}
