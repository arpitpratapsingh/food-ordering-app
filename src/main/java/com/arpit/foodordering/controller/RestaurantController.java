package com.arpit.foodordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpit.foodordering.dto.ResponseStructure;
import com.arpit.foodordering.dto.RestaurantDto;
import com.arpit.foodordering.entity.MenuItems;
import com.arpit.foodordering.entity.Restaurant;
import com.arpit.foodordering.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;
	
	//1. add restaurant
	@PostMapping
	public ResponseEntity<ResponseStructure<Restaurant>> addRestaurant(@RequestBody RestaurantDto restaurantDto){
		ResponseStructure<Restaurant> response = restaurantService.addRestaurant(restaurantDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	//2. get all restaurant
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Restaurant>>> getRestaurant(){
		ResponseStructure<List<Restaurant>> response = restaurantService.getRestaurant();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//3. get by id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Restaurant>> getRestaurantById(@PathVariable Long id){
		ResponseStructure<Restaurant> response = restaurantService.getRestaurantById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//4. update restaurant
	@PatchMapping("/{id}")
	public ResponseEntity<ResponseStructure<Restaurant>> updateRestaurant(@RequestBody RestaurantDto restaurantDto, @PathVariable Long id){
		ResponseStructure<Restaurant> response = restaurantService.updateRestaurant(restaurantDto, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//5. delete restaurant
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteRestaurant(@PathVariable Long id){
		ResponseStructure<String> response = restaurantService.deleteRestaurant(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	//6. get restaurant by location
	@GetMapping("/location/{location}")
	public ResponseEntity<ResponseStructure<List<Restaurant>>> getRestaurantByLocation(@PathVariable String location){
		ResponseStructure<List<Restaurant>> response = restaurantService.getRestaurantByLocation(location);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	//7. get restaurant by name
	@GetMapping("/name/{name}")
	public ResponseEntity<ResponseStructure<Restaurant>> getRestaurantByName(@PathVariable String name){
		ResponseStructure<Restaurant> response = restaurantService.getRestaurantByName(name);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	//8. get by rating
	@GetMapping("/rating/{rating}")
	public ResponseEntity<ResponseStructure<List<Restaurant>>> getRestaurantByRating(@PathVariable Double rating){
		ResponseStructure<List<Restaurant>> response = restaurantService.getRestaurantByRating(rating);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	//9. get menu of a restaurant
	@GetMapping("/{restaurantId}/menu")
	public ResponseEntity<ResponseStructure<List<MenuItems>>> getRestaurantMenu(@PathVariable Long restaurantId){
		ResponseStructure<List<MenuItems>> response = restaurantService.getRestaurantMenu(restaurantId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
