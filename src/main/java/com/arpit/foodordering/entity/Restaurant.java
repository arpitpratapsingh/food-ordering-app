package com.arpit.foodordering.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long restaurantId;
		
	private String restaurantName;
	
	private String restaurantLocation;
	private Double restaurantRating;

	@OneToMany(mappedBy = "restaurant")
	private List<MenuItems> menuItems = new ArrayList<>();
	
}
