package com.arpit.foodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpit.foodordering.entity.MenuItems;

public interface MenuItemRepository extends JpaRepository<MenuItems, Long> {

}
