package com.arpit.foodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpit.foodordering.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

}
