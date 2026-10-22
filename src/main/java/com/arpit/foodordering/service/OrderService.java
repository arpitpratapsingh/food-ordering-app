package com.arpit.foodordering.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpit.foodordering.dto.ResponseStructure;
import com.arpit.foodordering.entity.Order;
import com.arpit.foodordering.enums.Status;
import com.arpit.foodordering.exception.OrderNotFoundException;
import com.arpit.foodordering.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	//1. list orders
	
	//2. get all orders
	public ResponseStructure<List<Order>> getAllOrders(){
		
		List<Order> orders = orderRepository.findAll();
		
		if(orders.isEmpty()) {
			throw new OrderNotFoundException("Orders do not exist");
		}
		
		ResponseStructure<List<Order>> response = new ResponseStructure<>();
		response.setMessage("No order exist");
		response.setData(orders);
		
		return response;
	}
	
	//3. get all orders of a customer
	
	//4. get by id
	public ResponseStructure<Order> getById(Long id){
		
		Optional<Order> opt = orderRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new OrderNotFoundException("Orders do not exist");
		}
		
		ResponseStructure<Order> response = new ResponseStructure<>();
		response.setMessage("Order does not exist");
		response.setData(opt.get());
		
		return response;
	}
	
	//5. update order status
	public ResponseStructure<Order> updateStatus(Long id, Status status){
		
		if(status == null) {
			throw new IllegalArgumentException("Status can not be null");
		}
		
		Optional<Order> opt = orderRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new OrderNotFoundException("No order is assoicated with order id : "+ id);
		}
		
		Order order = opt.get();
		order.setStatus(status);
		
		Order savedOrder = orderRepository.save(order);
		
		ResponseStructure<Order> response = new ResponseStructure<>();
		response.setMessage("Order status updated successfully");
		response.setData(savedOrder);;
		
		return response;
		
		
	}
	
	//6. cancel order
	
	//7. get by status
	public ResponseStructure<List<Order>> getByStatus(Status status){
		
		if(status == null) {
			throw new IllegalArgumentException("Status can not be null");
		}
		
		List<Order> orders = orderRepository.findAllByStatus(status);
		
		if(orders.isEmpty()) {
			throw new OrderNotFoundException("Order is empty");
		}
		
		ResponseStructure<List<Order>> response = new ResponseStructure<>();
		response.setMessage("Orders retrieved successfully");
		response.setData(orders);
		
		return response;
		
	}
	
	//8. get by date
	public ResponseStructure<List<Order>> getByOrderDate(LocalDateTime orderDate){
		
		if(orderDate == null) {
			throw new IllegalArgumentException("Date can not be null");
		}
		
		List<Order> orders = orderRepository.findByOrderDate(orderDate);
		
		if(orders.isEmpty()) {
			throw new OrderNotFoundException("Order is empty");
		}
		
		ResponseStructure<List<Order>> response = new ResponseStructure<>();
		response.setMessage("Orders retrieved successfully");
		response.setData(orders);
		
		return response;
		
	}
	//9. get all order placed in a restaurant
	
}
