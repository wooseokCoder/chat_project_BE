package com.example.demo.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.exception.SessionCheckException;
import com.example.demo.session.UserSession;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ChatInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession)session.getAttribute("session");
		String sHttpMethod = request.getMethod(); // 요청 HTTP 메소드
		// OPTIONS는 axios에서 해당 url이 사용가능한지 확인차 들어오는 요청이며
		// 본 요청은 put, post, delete이다.(OPTIONS과 본 요청이 두개가 동시에 들어온다)
		if(!sHttpMethod.equals("OPTIONS") && !sHttpMethod.equals("GET") && (userSession == null || userSession.getUserId() == null)) {
			ObjectMapper objectMapper = new ObjectMapper(); 
			Map<String,Object> mResult = new HashMap<String,Object>();
			mResult.put("errMsg","세션이 없습니다.");
			mResult.put("exceptionClass","SessionCheckException");
			response.setContentType("text/html; charset=utf-8");
	        response.getWriter().write(objectMapper.writeValueAsString(mResult));
	        response.setStatus(500);
			throw new SessionCheckException("세션이 없습니다.");
		}else {
			return true;
		}
	} 
	
}
