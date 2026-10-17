package com.arpit.foodordering.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDto {
	
	private String restaurantName;
	
	private String restaurantLocation;
	private Double restaurantRating;
}
