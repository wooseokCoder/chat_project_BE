package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping("/hello11")
	public String main11() {  
		System.out.println("hello???");
		return "hello";
	}
	@GetMapping("/hello123")
	public String main1() {  
		System.out.println("hello???");
		return "hello";
	}
}
