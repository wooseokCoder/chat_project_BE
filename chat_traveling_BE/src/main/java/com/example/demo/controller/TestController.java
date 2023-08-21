package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TestController {
	
	@PostMapping("/hello133333")
	public String main11() throws JsonProcessingException { 
		System.out.println("hello11");
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String,Object>> mList = new ArrayList<Map<String,Object>>();
		Map<String,Object> mMap = new HashMap<String,Object>();
		mMap.put("sendChat","안녕하세요");
		mMap.put("receiveChat","반갑습니다");
		mList.add(mMap);
		return objectMapper.writeValueAsString(mList);
	}
	@GetMapping("/hello123")
	public String main1() {  
		System.out.println("hello???");
		return "{[{sendChat:\"안녕하세요\",receiveChat:\"반갑습니다\"}]}";
	}
}
