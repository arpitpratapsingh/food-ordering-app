package com.arpit.foodordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpit.foodordering.dto.MenuItemsDto;
import com.arpit.foodordering.dto.ResponseStructure;
import com.arpit.foodordering.entity.MenuItems;
import com.arpit.foodordering.service.MenuItemService;

@RequestMapping("/menuitem")
@RestController
public class MenuItemController {

	@Autowired
	MenuItemService menuItemService;
	
	//1. Add item to a restaurant
	@PostMapping("/restaurant/{restaurantId}")
	public ResponseEntity<ResponseStructure<MenuItems>> addItemsToRestaurant(@RequestBody MenuItemsDto menuItemDto, @PathVariable Long restaurantId){
		ResponseStructure<MenuItems> response = menuItemService.addItemsToRestaurant(menuItemDto, restaurantId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	//2. Get all Items
	@GetMapping
	public ResponseEntity<ResponseStructure<List<MenuItems>>> getAllItems(){
		ResponseStructure<List<MenuItems>> response = menuItemService.getAllItems();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//3. Get by ID
	@GetMapping("/{menuId}")
	public ResponseEntity<ResponseStructure<MenuItems>> getByMenuId(@PathVariable Long menuId){
		ResponseStructure<MenuItems> response = menuItemService.getByMenuId(menuId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//4. Update price and availability
	@PatchMapping("/{menuItemId}")
	public ResponseEntity<ResponseStructure<MenuItems>> updateMenuItem(@RequestBody MenuItemsDto menuItemsDto, @PathVariable Long menuItemId){
		ResponseStructure<MenuItems> response = menuItemService.updateMenuItem(menuItemsDto, menuItemId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//5. Sort by price
	@GetMapping("/sort/price")
	public ResponseEntity<ResponseStructure<List<MenuItems>>> sortByPrice(){
		ResponseStructure<List<MenuItems>> response = menuItemService.sortByPrice();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	//6. get items by name
	@GetMapping("/itemName/{name}")
	public ResponseEntity<ResponseStructure<List<MenuItems>>> getItemsByName(@PathVariable String name){
		ResponseStructure<List<MenuItems>> response = menuItemService.getItemsByName(name);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//7. get all the items in a restaurant
	@GetMapping("/restaurantId/{restaurantId}")
	public ResponseEntity<ResponseStructure<List<MenuItems>>> getItemsByRestaurant(@PathVariable Long restaurantId){
		ResponseStructure<List<MenuItems>> response = menuItemService.getItemsByRestaurant(restaurantId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
