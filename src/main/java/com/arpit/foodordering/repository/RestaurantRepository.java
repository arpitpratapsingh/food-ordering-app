package com.arpit.foodordering.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpit.foodordering.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
	public boolean existsByRestaurantName(String restaurantName);
	public List<Restaurant> findByRestaurantLocation(String restaurantLocation);
	public Optional<Restaurant> findByRestaurantName(String restaurantName);
	public List<Restaurant> findByRestaurantRating(Double restaurantRating);
}
