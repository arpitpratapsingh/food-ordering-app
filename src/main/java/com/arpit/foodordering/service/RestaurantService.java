package com.arpit.foodordering.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpit.foodordering.dto.ResponseStructure;
import com.arpit.foodordering.dto.RestaurantDto;
import com.arpit.foodordering.entity.MenuItems;
import com.arpit.foodordering.entity.Restaurant;
import com.arpit.foodordering.exception.DuplicateRestaurantException;
import com.arpit.foodordering.exception.MenuItemNotFoundException;
import com.arpit.foodordering.exception.RestaurantNotFoundException;
import com.arpit.foodordering.repository.MenuItemsRepository;
import com.arpit.foodordering.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	MenuItemsRepository menuItemRepository;
	
	//1. Add restaurant
	public ResponseStructure<Restaurant> addRestaurant(RestaurantDto restaurantDto){
		
		//check for null, only spaces, and empty string -> name
		if(restaurantDto.getRestaurantName() == null ||
				restaurantDto.getRestaurantName().trim().isEmpty()) {
			throw new IllegalArgumentException("Restaurant name cannot be empty");
		}
		
		String name = restaurantDto.getRestaurantName().trim();
		
		if(restaurantRepository.existsByName(name)) {
			throw new DuplicateRestaurantException("Restaurant with name "+ name +" already exists");
		}
		
		//-> location
		if(restaurantDto.getRestaurantLocation() == null ||
				restaurantDto.getRestaurantLocation().trim().isEmpty()) {
			throw new IllegalArgumentException("Restaurant location cannot be empty");
		}
		
		String location = restaurantDto.getRestaurantLocation().trim();
		
		if(restaurantDto.getRestaurantRating() == null ||
				restaurantDto.getRestaurantRating() < 0.0 ||
				restaurantDto.getRestaurantRating() > 5.0) {
			throw new IllegalArgumentException("Rating must be between 0 and 5");
		}
		
		
		Restaurant restaurant = new Restaurant();
		
		restaurant.setRestaurantName(name);
		restaurant.setRestaurantLocation(location);
		restaurant.setRestaurantRating(restaurantDto.getRestaurantRating());
		
		restaurantRepository.save(restaurant);
		
		ResponseStructure<Restaurant> response = new ResponseStructure<>();
		response.setMessage("Restaurant added successfully");
		response.setData(restaurant);
		
		return response;
	}
	
	
	//2. Get restaurant
	public ResponseStructure<List<Restaurant>> getRestaurant(){
		
		List<Restaurant> restaurants = restaurantRepository.findAll();
		
		if(restaurants.isEmpty()) {
			throw new RestaurantNotFoundException("Restaurant not found");
		}
		
		ResponseStructure<List<Restaurant>> response = new ResponseStructure<>();
		response.setMessage("Restaurants retrieved successfully");
		response.setData(restaurants);
		
		return response;
	}
	
	
	//3. Get restaurant by ID
	public ResponseStructure<Restaurant> getRestaurantById(Long id){
		
		Optional<Restaurant> opt = restaurantRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new RestaurantNotFoundException("Restaurant not found");
		}
		
		ResponseStructure<Restaurant> response = new ResponseStructure<>();
		response.setMessage("Restaurant retrieved successfully");
		response.setData(opt.get());
		
		return response;
	}
	
	//4. Update restaurant
	public ResponseStructure<Restaurant> updateRestaurant(RestaurantDto restaurantDto, Long id) {
		
		Optional<Restaurant> opt = restaurantRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new RestaurantNotFoundException("Restaurant associated with id "+ id +" does not exist");
		}
		
		Restaurant restaurant = opt.get();
		
		// name
		if(restaurantDto.getRestaurantName() != null) {
			String name = restaurantDto.getRestaurantName().trim();
			
			if(name.isEmpty()) {
				throw new IllegalArgumentException("Restaurant name cannot be empty");
			}
			
			if(restaurantRepository.existsByName(name) &&
					!name.equalsIgnoreCase(restaurant.getRestaurantName())) {
				throw new DuplicateRestaurantException("Please choose another name");
			}
			restaurant.setRestaurantName(name);
			
		}
		// location
		if(restaurantDto.getRestaurantLocation() != null) {
			
			String location = restaurantDto.getRestaurantLocation().trim();
			
			if(location.isEmpty()) {
				throw new IllegalArgumentException("Restaurant location cannot be empty");
			}
			
			restaurant.setRestaurantLocation(location);
		}
		// rating
		if(restaurantDto.getRestaurantRating() != null) {
			
			Double rating = restaurantDto.getRestaurantRating();
			
			if(rating < 0.0 || rating > 5.0) {
				throw new IllegalArgumentException("Rating must be between 0.0 and 5.0");
			}
			
			restaurant.setRestaurantRating(rating);
		}
		
		restaurantRepository.save(restaurant);
		
		ResponseStructure<Restaurant> response = new ResponseStructure<Restaurant>();
		response.setData(restaurant);
		response.setMessage("Restaurant updated successfully");
		return response;
	}
	
	
	//5. delete restaurant
	public ResponseStructure<String> deleteRestaurant(Long id){
		
		if(!restaurantRepository.existsById(id)) {
			throw new RestaurantNotFoundException("Restaurant associated with id "+ id +" does not exist");
		}
		
		restaurantRepository.deleteById(id);
		
		ResponseStructure<String> response = new ResponseStructure<>();
		
		response.setData(null);
		response.setMessage("Restaurant deleted successfully");
		
		return response;
	}
	
	
	//6. get restaurant by location
	public ResponseStructure<List<Restaurant>> getRestaurantByLocation(String location){
		
		if(location == null || location.trim().isEmpty()) {
			throw new IllegalArgumentException("Location cannot be null or empty");
		}
		
		List<Restaurant> restaurants = restaurantRepository.findByLocation(location.trim());
		
		if(restaurants.isEmpty()) {
			throw new RestaurantNotFoundException("There is no restaurant at given location");
		}
		
		ResponseStructure<List<Restaurant>> response = new ResponseStructure<>();
		response.setData(restaurants);
		response.setMessage("All restaurants fetched successfully");
		
		return response;
	}
	
	
	//7. get restaurant by name
	public ResponseStructure<Restaurant> getRestaurantByName(String name){
		
		if(name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name can not be null or empty");
		}
		
		Optional<Restaurant> opt = restaurantRepository.findByName(name.trim());
		
		if(opt.isEmpty()) {
			throw new RestaurantNotFoundException("Restaurant with name "+ name +" does not exist");
		}
		
		ResponseStructure<Restaurant> response = new ResponseStructure<>();
		response.setData(opt.get());
		response.setMessage("Restaurant fetched successfully");
		
		return response;
		
	}
	
	
	//8. get restaurant by rating
	public ResponseStructure<List<Restaurant>> getRestaurantByRating(Double rating){
		
		if(rating == null || rating < 0.0 || rating > 5.0) {
			throw new IllegalArgumentException("Rating must be between 0.0 and 5.0");
		}
		
		List<Restaurant> restaurants = restaurantRepository.findByRating(rating);
		
		if(restaurants.isEmpty()) {
			throw new RestaurantNotFoundException("No restaurant found with rating " + rating);
		}
		
		ResponseStructure<List<Restaurant>> response = new ResponseStructure<>();
		response.setData(restaurants);
		response.setMessage("Restaurant fetched successfully");
		
		return response;
		
	}
	
	
	//9. get menu of a restaurant
	public ResponseStructure<List<MenuItems>> getRestaurantMenu(Long restaurantId){
		
		Optional<Restaurant> opt = restaurantRepository.findById(restaurantId);
		
		if(opt.isEmpty()) {
			throw new RestaurantNotFoundException("Restaurant associated with id " + restaurantId + " does not exist");
		}
		
		Restaurant restaurant = opt.get();
				
		List<MenuItems> menu = menuItemRepository.findByRestaurant(restaurant);
		
		if(menu.isEmpty()) {
			throw new MenuItemNotFoundException("No menu items found for restaurant with id " + restaurantId);
		}
		
		ResponseStructure<List<MenuItems>> response = new ResponseStructure<List<MenuItems>>();
		response.setMessage("Menu fetched successfully");
		response.setData(menu);
		
		return response;
		
	}
	
}
