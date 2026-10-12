package com.arpit.foodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpit.foodordering.entity.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

}
