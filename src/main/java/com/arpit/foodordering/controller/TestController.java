package com.arpit.foodordering.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/api/test")
	public String testApplication() {
		return "Food ordering app backend is running!";
	}

}
