package com.arpit.foodordering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpit.foodordering.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public boolean existsByEmail(String email);
	public Customer findByEmail(String email);
	public boolean existsByContactNumber(String contactNumber);
	public Customer findByContactNumber(String contactNumber);
	public List<Customer> findByName(String name);
	
}
