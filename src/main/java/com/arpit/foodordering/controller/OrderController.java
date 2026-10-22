package com.arpit.foodordering.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arpit.foodordering.dto.OrderDto;
import com.arpit.foodordering.dto.ResponseStructure;
import com.arpit.foodordering.entity.Order;
import com.arpit.foodordering.enums.Status;
import com.arpit.foodordering.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	//1. list order
	
	//2. get all orders
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Order>>> getAllOrders(){
		ResponseStructure<List<Order>> response = orderService.getAllOrders();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//3. get all orders of a customer
	
	//4. get by id
	@GetMapping("/id/{orderId}")
	public ResponseEntity<ResponseStructure<Order>> getById(@PathVariable Long orderId){
		ResponseStructure<Order> response = orderService.getById(orderId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//5. update order status
	@PatchMapping("/update/status/{id}")
	public ResponseEntity<ResponseStructure<Order>> updateStatus(@PathVariable Long id, @RequestParam Status status){
		ResponseStructure<Order> response = orderService.updateStatus(id, status);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//6. cancel order
	
	
	//7. get by status
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Order>>> getByStatus(@PathVariable Status status){
		ResponseStructure<List<Order>> response = orderService.getByStatus(status);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//8. get by date
	@GetMapping("/date/{orderDate}")
	public ResponseEntity<ResponseStructure<List<Order>>> getByOrderDate(@PathVariable LocalDateTime orderDate){
		ResponseStructure<List<Order>> response = orderService.getByOrderDate(orderDate);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//9. get all orders placed in a restaurant
}











