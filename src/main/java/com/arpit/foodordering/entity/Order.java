package com.arpit.foodordering.entity; 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.arpit.foodordering.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name= "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime orderDateTime;

	@Enumerated(EnumType.STRING)
	private Status status;
	private Double totalAmount;
	
	@JoinColumn(name = "customer_id")
	@ManyToOne
	private Customer customer;

	@OneToMany(mappedBy = "order")
	private List<OrderItems> orderItems = new ArrayList<>();

	@OneToOne(mappedBy = "order")
	private Payment payment;

}
