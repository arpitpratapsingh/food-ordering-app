package com.arpit.foodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpit.foodordering.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public boolean existsByEmail(String email);
	public boolean existsByContactNumber(String contactNumber);
}
