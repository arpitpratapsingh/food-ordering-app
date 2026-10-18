package com.arpit.foodordering.dto;

import com.arpit.foodordering.entity.Restaurant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemsDto {
	
	private String itemName;
	private Double itemPrice;
	private Boolean itemAvailability;
	
}
