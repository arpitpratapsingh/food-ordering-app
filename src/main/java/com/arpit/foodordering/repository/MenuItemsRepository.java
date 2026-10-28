package com.arpit.foodordering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpit.foodordering.entity.MenuItems;
import com.arpit.foodordering.entity.Restaurant;

public interface MenuItemsRepository extends JpaRepository<MenuItems, Long> {
	List<MenuItems> findByRestaurant(Restaurant restaurant);
	List<MenuItems> findByRestaurant_RestaurantId(Long restaurantId);
	List<MenuItems> findByItemName(String name);
	List<MenuItems> findAllByOrderByItemPriceAsc();
	List<MenuItems> findAllByOrderByItemPriceDesc();
}
