package com.arpit.foodordering.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpit.foodordering.dto.MenuItemsDto;
import com.arpit.foodordering.dto.ResponseStructure;
import com.arpit.foodordering.entity.MenuItems;
import com.arpit.foodordering.entity.Restaurant;
import com.arpit.foodordering.exception.MenuItemNotFoundException;
import com.arpit.foodordering.exception.RestaurantNotFoundException;
import com.arpit.foodordering.repository.MenuItemsRepository;
import com.arpit.foodordering.repository.RestaurantRepository;

@Service
public class MenuItemService {
	@Autowired
	MenuItemsRepository menuItemsRepository;
	@Autowired
	RestaurantRepository restaurantRepository;
	
	public ResponseStructure<MenuItems> addItemsToRestaurant(MenuItemsDto menuItemDto, Long restaurantId){
		Optional<Restaurant> opt = restaurantRepository.findById(restaurantId);
		
		if(opt.isEmpty()) {
			throw new RestaurantNotFoundException("Restaurant with id " + restaurantId + " does not exist");
		}
		
		Restaurant restaurant = opt.get();
		
		MenuItems menuItem = new MenuItems();
		
		menuItem.setItemName(menuItemDto.getItemName());
		menuItem.setItemPrice(menuItemDto.getItemPrice());
		menuItem.setItemAvailability(menuItemDto.getItemAvailability());
		
		// Establish relationship
		menuItem.setRestaurant(restaurant);
		
		MenuItems savedItem = menuItemsRepository.save(menuItem);
		
		ResponseStructure<MenuItems> response = new ResponseStructure<>();
		response.setMessage("Menu item adde successfully");
		response.setData(savedItem);
		return response;
	}
	
	
	//2. Get all Items
	public ResponseStructure<List<MenuItems>> getAllItems(){
		
		List<MenuItems> items = menuItemsRepository.findAll();
		
		if(items.isEmpty()) {
			throw new  MenuItemNotFoundException("Menu is empty");
		}
		
		ResponseStructure<List<MenuItems>> response = new ResponseStructure<>();
		response.setMessage("All menu items fetched successfully");
		response.setData(items);
		
		return response;
	}
	
	//3. Get by ID
	public ResponseStructure<MenuItems> getByMenuId(Long menuId){
		
		Optional<MenuItems> opt = menuItemsRepository.findById(menuId);
		
		if(opt.isEmpty()) {
			throw new MenuItemNotFoundException("This id is not associated with any menu item");
		}
		
		ResponseStructure<MenuItems> response = new ResponseStructure<>();
		response.setMessage("Menu item fetched successfully");
		response.setData(opt.get());
		return response;
		
	}
	
	//4. Update price and availability
	public ResponseStructure<MenuItems> updateMenuItem(MenuItemsDto menuItemDto, Long menuItemId){
		
		Optional<MenuItems> opt = menuItemsRepository.findById(menuItemId);
		
		if(opt.isEmpty()) {
			throw new MenuItemNotFoundException("Menu item with id "+menuItemId+" does not exist");
		}
		
		MenuItems menuItem = opt.get();
		
		// update price
		if(menuItemDto.getItemPrice() != null) {
			
			if(menuItemDto.getItemPrice() <= 0) {
				throw new IllegalArgumentException("Item price must be greater than 0");
			}
			
			menuItem.setItemPrice(menuItemDto.getItemPrice());
		}
		// update availability
		if(menuItemDto.getItemAvailability() != null) {
			
			menuItem.setItemAvailability(menuItemDto.getItemAvailability());
			
		}
		
		MenuItems updatedItem = menuItemsRepository.save(menuItem);
		
		ResponseStructure<MenuItems> response = new ResponseStructure<>();
		response.setMessage("Menu item updated successfully");
		response.setData(updatedItem);
		
		return response;
	}
	
	//5. Sort by price
	public ResponseStructure<List<MenuItems>> sortByPrice(){
		
		List<MenuItems> items = menuItemsRepository.findAllByOrderByItemPriceAsc();
		
		if(items.isEmpty()) {
			throw new MenuItemNotFoundException("No menu items found");
		}
		
		ResponseStructure<List<MenuItems>> response = new ResponseStructure<>();
		response.setMessage("Items sorted by price successfully");
		response.setData(items);
		
		return response;
	}
	
	//6. get items by name
	public ResponseStructure<List<MenuItems>> getItemsByName(String name){
		
		List<MenuItems> items = menuItemsRepository.findByItemName(name);
		
		if(items.isEmpty()) {
			throw new MenuItemNotFoundException("No menu items found with name " + name);
		}
		
		ResponseStructure<List<MenuItems>> response = new ResponseStructure<>();
		response.setMessage("All items with name "+name+" fetched successfully");
		response.setData(items);
		return response;
	}
	
	//7. get all the items in a restaurant
	public ResponseStructure<List<MenuItems>> getItemsByRestaurant(Long restaurantId){
		
		Optional<Restaurant> opt = restaurantRepository.findById(restaurantId);
		
		if(opt.isEmpty()) {
			throw new RestaurantNotFoundException("Restaurant with id "+ restaurantId+ " does not exist");
		}
		
		List<MenuItems> items = menuItemsRepository.findByRestaurantId(restaurantId);
		
		if(items.isEmpty()) {
			throw new MenuItemNotFoundException("Menu is empty");
		}
		
		ResponseStructure<List<MenuItems>> response = new ResponseStructure<>();
		response.setMessage("Items fetched successfully");
		response.setData(items);
		return response;
	}
	
}
