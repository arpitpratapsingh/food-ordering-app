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
	public List<Order> findByOrderDateTime(LocalDateTime orderDateTime);
	
	@Query("""
		    SELECT DISTINCT o
		    FROM Order o
		    JOIN o.orderItems oi
		    JOIN oi.menuItem mi
		    WHERE mi.restaurant.restaurantId = :restaurantId
		    """)
		List<Order> findOrdersByRestaurantId(
		        @Param("restaurantId") Long restaurantId);
}
