package com.arpit.foodordering.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpit.foodordering.dto.ResponseStructure;
import com.arpit.foodordering.entity.Customer;
import com.arpit.foodordering.entity.Order;
import com.arpit.foodordering.entity.Restaurant;
import com.arpit.foodordering.enums.Status;
import com.arpit.foodordering.exception.CustomerNotFoundException;
import com.arpit.foodordering.exception.OrderNotFoundException;
import com.arpit.foodordering.exception.RestaurantNotFoundException;
import com.arpit.foodordering.repository.CustomerRepository;
import com.arpit.foodordering.repository.OrderRepository;
import com.arpit.foodordering.repository.RestaurantRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	RestaurantRepository restaurantRepository;
	
	//1. get all orders
	public ResponseStructure<List<Order>> getAllOrders(){
		
		List<Order> orders = orderRepository.findAll();
		
		if(orders.isEmpty()) {
			throw new OrderNotFoundException("Orders do not exist");
		}
		
		ResponseStructure<List<Order>> response = new ResponseStructure<>();
		response.setMessage("Orders retrived successfully");
		response.setData(orders);
		
		return response;
	}
	
	//2. get orders of a customer
	public ResponseStructure<List<Order>> getOrdersByCustomer(Long customerId){
		
		Optional<Customer> opt = customerRepository.findById(customerId);
		
		if(opt.isEmpty()) {
			throw new CustomerNotFoundException("Customer associated with customer id : "+ customerId +"does not exist");
		}
		
		Customer customer = opt.get();
		
		List<Order> orderList = customer.getOrderList();
		
		ResponseStructure<List<Order>> response = new ResponseStructure<List<Order>>();
		response.setMessage("Orders retrived successfully");
		response.setData(orderList);
		
		return response;
		
	}
	
	//3. get by id
	public ResponseStructure<Order> getById(Long id){
		
		Optional<Order> opt = orderRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new OrderNotFoundException("Orders do not exist");
		}
		
		ResponseStructure<Order> response = new ResponseStructure<>();
		response.setMessage("Order retrieved successfully");
		response.setData(opt.get());
		
		return response;
	}
	
	//4. update order status
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
	
	//5. cancel order
	public ResponseStructure<Order> cancelOrder(Long orderId){
		
		Optional<Order> opt = orderRepository.findById(orderId);
		
		if(opt.isEmpty()) {
			throw new OrderNotFoundException("Order with order ID : "+orderId+" does not exist");
		}
		
		Order order = opt.get();
		
		if (order.getStatus() == Status.COMPLETED) {
		    throw new IllegalArgumentException("Delivered orders cannot be cancelled");
		}

		if (order.getStatus() == Status.CANCELLED) {
		    throw new IllegalArgumentException("Order is already cancelled");
		}
		
		order.setStatus(Status.CANCELLED);
		
		Order updatedOrder = orderRepository.save(order);
		
		ResponseStructure<Order> response = new ResponseStructure<Order>();
		
		response.setMessage("You have successfully cancelled your order");
		response.setData(updatedOrder);
		
		return response;
		
		
	}
	
	//6. get by status
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
	
	//7. get by date
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
	
	//8. get all order placed in a restaurant
	public ResponseStructure<List<Order>> getOrdersByRestaurant(Long restaurantId){
		
		Optional<Restaurant> opt = restaurantRepository.findById(restaurantId);
		
		if(opt.isEmpty()) {
			throw new RestaurantNotFoundException("Restaurant not found");
		}
		
		List<Order> orderList = orderRepository.findOrdersByRestaurantId(restaurantId);
		
		if(orderList.isEmpty()) {
			throw new OrderNotFoundException("No orders found for this restaurant");
		}
		
		ResponseStructure<List<Order>> response = new ResponseStructure<List<Order>>();
		
		response.setMessage("Orders fetched successfully");
		response.setData(orderList);
		
		return response;
		
	}
}
