package com.arpit.foodordering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpit.foodordering.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	
}
