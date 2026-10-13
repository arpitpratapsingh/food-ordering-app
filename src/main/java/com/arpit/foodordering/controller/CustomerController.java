package com.arpit.foodordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpit.foodordering.dto.CustomerDto;
import com.arpit.foodordering.dto.ResponseStructure;
import com.arpit.foodordering.entity.Customer;
import com.arpit.foodordering.repository.CustomerRepository;
import com.arpit.foodordering.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	//1. create customer
	@PostMapping
	public ResponseEntity<ResponseStructure<Customer>> creteCustomer(@RequestBody CustomerDto customerDto){
		ResponseStructure<Customer> response = customerService.createCustomer(customerDto);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	

	//2. get all customers
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomers(){
		
		ResponseStructure<List<Customer>> response = customerService.getAllCustomers();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	
	//3. get by id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Customer>> getById(@PathVariable Long id){
		ResponseStructure<Customer> response = customerService.getById(id);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	//4. update customer
	@PatchMapping("/{id}")
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody CustomerDto customerDto, Long id){
		ResponseStructure<Customer> response = customerService.updateCustomer(customerDto, id);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}


	//5. delete
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
