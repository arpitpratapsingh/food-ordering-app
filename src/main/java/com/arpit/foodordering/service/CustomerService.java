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
	
	
	//5. Delete Customer
	public ResponseStructure<String> delete(Long id){
		if(!customerRepository.existsById(id)) {
			throw new CustomerNotFoundException("Customer with give id "+id+"does not exist");
		}
		customerRepository.deleteById(id);
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setData(null);
		response.setMessage("Customer deleted successfully");
		return response;
		
	}
	
	
	//6. Get By Contact
	public ResponseStructure<Customer> getByContact(String contactNumber){
		if(!customerRepository.existsByContactNumber(contactNumber)) {
			throw new CustomerNotFoundException("This contact number does not belong to any customer");
		}
		ResponseStructure<Customer> response = new ResponseStructure<>();
		response.setData(customerRepository.findByContactNumber(contactNumber));
		response.setMessage("Customer fetched from database successfully");
		
		return response;
	}


	//7. Get By Email
	public ResponseStructure<Customer> getByEmail(String customerEmail){
		if(!customerRepository.existsByContactNumber(customerEmail)) {
			throw new CustomerNotFoundException("This emailId does not belong to any customer");
		}
		ResponseStructure<Customer> response = new ResponseStructure<>();
		response.setData(customerRepository.findByEmail(customerEmail));
		response.setMessage("Customer fetched from database successfully");
		
		return response;
	}
	
	
	//8. Get By Name
	public ResponseStructure<List<Customer>> getByName(String name){
		if(!customerRepository.existsByName(name)) {
			throw new CustomerNotFoundException("Customer with given name "+ name +" does not exist");
		}
		ResponseStructure<List<Customer>> response = new ResponseStructure<>();
		response.setData(customerRepository.findByName(name));
		
		return response;
	}
	
	
	
	
	
	
	
	
}
