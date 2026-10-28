package com.arpit.foodordering.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arpit.foodordering.entity.Order;
import com.arpit.foodordering.enums.Status;

public interface OrderRepository extends JpaRepository<Order, Long> {

	public List<Order> findAllByStatus(Status status);
	public List<Order> findByOrderDate(LocalDateTime orderDate);
	
	@Query("Select distinct o from order o join o.orderItems oi join oi.menuItem mi where mi.restaurant.restaurantId = :restaurantId")
	public List<Order> findOrdersByRestaurantId(@Param("restaurantId")Long restaurantId);
}
