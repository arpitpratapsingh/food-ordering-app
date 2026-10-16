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
			throw new CustomerNotFoundException("Customer associated with id "+id+" does not exist");
		}
		Customer customer = opt.get();
		//email
		if(customerDto.getEmail() != null) {
			String email = customerDto.getEmail().trim();
			
			if(customerRepository.existsByEmail(email)
					&& !email.equalsIgnoreCase(customer.getEmail())) {
				throw new DuplicateCustomerException("This email id already exists");
			}
			customer.setEmail(email);
					
		}
		
		
		// contact number
		if(customerDto.getContactNumber() != null) {
			String contact = customerDto.getContactNumber().trim();
			
			if(customerRepository.existsByContactNumber(contact) 
					&& !contact.equals(customer.getContactNumber())) {
				throw new DuplicateCustomerException("This contact number already exists");
			}
			customer.setContactNumber(contact);
		}
		
		// address
		if(customerDto.getAddress() != null) {
			customer.setAddress(customerDto.getAddress().trim());
		}
		
		// name
		if(customerDto.getName() != null) {
			customer.setName(customerDto.getName().trim());
		}

		customerRepository.save(customer);
		
		ResponseStructure<Customer> response = new ResponseStructure<>();
		response.setData(customer);
		response.setMessage("Customer updated successfully");
		
		return response;
	}
	
	//5. Delete Customer
	public ResponseStructure<String> delete(Long id){
		if(!customerRepository.existsById(id)) {
			throw new CustomerNotFoundException("Customer with ID " + id + " does not exist");
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
		response.setMessage("Customer fetched from successfully");
		
		return response;
	}


	//7. Get By Email
	public ResponseStructure<Customer> getByEmail(String customerEmail){
		if(!customerRepository.existsByEmail(customerEmail)) {
			throw new CustomerNotFoundException("Customer with email "+ customerEmail + " does not exist");
		}
		ResponseStructure<Customer> response = new ResponseStructure<>();
		response.setData(customerRepository.findByEmail(customerEmail));
		response.setMessage("Customer fetched successfully");
		
		return response;
	}
	
	
	//8. Get By Name
	public ResponseStructure<List<Customer>> getByName(String name){
		List<Customer> customers = customerRepository.findByName(name);
		if(customers.isEmpty()) {
			throw new CustomerNotFoundException("Custome with name "+ name +" does not exist");
		}
		ResponseStructure<List<Customer>> response = new ResponseStructure<>();
		response.setData(customers);
		response.setMessage("Customers fetched successfully");
		return response;
		
	}
	
	
}
