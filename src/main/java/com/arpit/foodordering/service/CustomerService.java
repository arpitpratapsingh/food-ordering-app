package com.arpit.foodordering.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpit.foodordering.dto.CustomerDto;
import com.arpit.foodordering.dto.ResponseStructure;
import com.arpit.foodordering.entity.Customer;
import com.arpit.foodordering.exception.CustomerNotFoundException;
import com.arpit.foodordering.exception.DuplicateCustomerException;
import com.arpit.foodordering.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	public CustomerRepository customerRepository;
	
	//1. Create customer
	public ResponseStructure<Customer> createCustomer(CustomerDto customerDto){
		
		// check whether the email already exists
		if(customerRepository.existsByEmail(customerDto.getEmail())){
			throw new DuplicateCustomerException("Customer already exists with email: "+ customerDto.getEmail());
		}

		// Convert DTO into Entity
		Customer customer = new Customer();
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setContactNumber(customerDto.getContactNumber());
		customer.setAddress(customerDto.getAddress());
		
		// Save the entity
		Customer savedCustomer = customerRepository.save(customer);
		
		// Prepare response
		ResponseStructure<Customer> response = new ResponseStructure<>();
		response.setMessage("Customer created successfully");
		response.setData(savedCustomer);
		
		return response;
		
	}

	//2. Get all customer
	public ResponseStructure<List<Customer>> getAllCustomers(){
		
		List<Customer> customers = customerRepository.findAll();
		
		if(customers.isEmpty()) {
			throw new CustomerNotFoundException("Customer does not exist.");
		}
		
		ResponseStructure<List<Customer>>response = new ResponseStructure<>();
		response.setMessage("Customers retrieved successfully");
		response.setData(customers);

		return response;
	}
	
	
	//3. Get By Id
	public ResponseStructure<Customer> getById(Long id){
		ResponseStructure<Customer> response = new ResponseStructure<Customer>();
		Optional<Customer> opt = customerRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new CustomerNotFoundException("Customer with id : "+id+" does not exist");
		}
		response.setData(opt.get());
		response.setMessage("Customer data fetched successfully");
		return response;
	}
	
	
	//4. Update Customer
	public ResponseStructure<Customer> updateCustomer(CustomerDto customerDto, Long id){

	Optional<Customer> opt = customerRepository.findById(id);
	
		if(opt.isEmpty()) {
			throw new CustomerNotFoundException("Customer with id : "+id+" does not exist");
		}
		
	Customer customer = opt.get();
	
		if(customerDto.getEmail() != null 
				&& !customerDto.getEmail().equalsIgnoreCase(customer.getEmail()) 
				&& customerRepository.existsByEmail(customerDto.getEmail().trim())) {
			throw new DuplicateCustomerException("This email id already exists in database");
		}
	
		if(customerDto.getContactNumber() != null 
				&& customer.getEmail() != customer.getContactNumber()
				&& customerRepository.existsByContactNumber(customerDto.getContactNumber())){
			throw new DuplicateCustomerException("This contact number already exists in database");
		}
		
		if(customerDto.getAddress() != null) {
			customer.setAddress(customerDto.getAddress().trim());
		}
		if(customerDto.getName() != null) {
			customer.setName(customerDto.getName());
		}
		
		customerRepository.save(customer);
		
		ResponseStructure<Customer> response = new ResponseStructure<>();
		response.setMessage("Customer updated successfully");
		response.setData(customer);
		return response;

	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
