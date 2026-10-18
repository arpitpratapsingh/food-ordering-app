package com.arpit.foodordering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpit.foodordering.entity.MenuItems;
import com.arpit.foodordering.entity.Restaurant;

public interface MenuItemsRepository extends JpaRepository<MenuItems, Long> {
	List<MenuItems> findByRestaurantId(Long restaurantId);
	List<MenuItems> findByItemName(String name);
	List<MenuItems> findAllByOrderByItemPriceAsc();
	List<MenuItems> findAllByOrderByItemPriceDesc();
}
