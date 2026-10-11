package com.arpit.foodordering.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Restaurent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private String restaurentName;
	
	private String restaurentLocation;
	private Double restaurentRating;
	
}
