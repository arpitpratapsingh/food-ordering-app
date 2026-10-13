package com.arpit.foodordering.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {
	
	private String name;
	private String email;
	private String contactNumber;
	private String address;
}
