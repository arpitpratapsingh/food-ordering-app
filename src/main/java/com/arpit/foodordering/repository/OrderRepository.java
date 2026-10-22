package com.arpit.foodordering.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpit.foodordering.entity.Order;
import com.arpit.foodordering.enums.Status;

public interface OrderRepository extends JpaRepository<Order, Long> {

	public List<Order> findAllByStatus(Status status);
	public List<Order> findByOrderDate(LocalDateTime orderDate);
}
