package com.arpit.foodordering.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpit.foodordering.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
	public boolean existsByName(String name);
	public List<Restaurant> findByLocation(String location);
	public Optional<Restaurant> findByName(String name);
	public List<Restaurant> findByRating(Double rating);
}
