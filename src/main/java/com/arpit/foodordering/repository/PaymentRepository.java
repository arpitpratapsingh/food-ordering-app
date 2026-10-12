package com.arpit.foodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpit.foodordering.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
