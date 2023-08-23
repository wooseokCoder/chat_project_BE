package com.example.demo.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class TestController {
	
	@PostMapping("/hello133333")
	public String main11(HttpServletRequest request, @RequestBody Map<String,Object> jsonData) throws JsonProcessingException { 
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("id"));
		session.setAttribute("id", "yjlee"); 
		System.out.println("hello11");
		ObjectMapper objectMapper = new ObjectMapper(); 
		List<Map<String,Object>> mList = new ArrayList<Map<String,Object>>();
		jsonData.put("chatAsw","반갑습니다");
		mList.add(jsonData);
		return objectMapper.writeValueAsString(jsonData);
	}
	@GetMapping("/hello123")
	public String main1() {  
		System.out.println("hello???");
		return "{[{sendChat:\"안녕하세요\",receiveChat:\"반갑습니다\"}]}";
	}
}
