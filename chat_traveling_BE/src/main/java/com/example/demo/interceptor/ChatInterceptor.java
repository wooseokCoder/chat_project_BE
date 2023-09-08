package com.example.demo.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.exception.SessionCheckException;
import com.example.demo.exception.TokenCheckException;
import com.example.demo.session.UserSession;
import com.fasterxml.jackson.core.JsonProcessingException;
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
		String sRequestPath = request.getServletPath(); // 요청 경로
		// OPTIONS는 axios에서 해당 url이 사용가능한지 확인차 들어오는 요청이며
		// 본 요청은 put, post, delete이다.(OPTIONS과 본 요청이 두개가 동시에 들어온다)
		if(!sHttpMethod.equals("OPTIONS") && !sHttpMethod.equals("GET")) {
			if(!sRequestPath.startsWith("/login") && !sRequestPath.startsWith("/error")) {
				Exception exception = null;
				if(userSession == null || userSession.getUserId() == null){
					exception = new SessionCheckException("세션이 없습니다.");
				}
				if(!session.getAttribute("csrfToken").equals(request.getHeader("csrfToken"))) {
					exception = new TokenCheckException("정상적인 요청 토큰이 아닙니다.");
				}
				if(exception != null) {
					setResponseErrorResult(response,exception);
					return false;
				}
			}
			// 정상적인 요청일 경우 csrfToken세팅
			synchronized(response) {
				String sToken = UUID.randomUUID().toString(); 
				request.getSession().setAttribute("csrfToken", sToken);
				response.setHeader("csrfToken", sToken);
			}
		}
		return true;
	}
	
	private HttpServletResponse setResponseErrorResult(HttpServletResponse response,Exception exception) throws JsonProcessingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper(); 
		Map<String,Object> mResult = new HashMap<String,Object>();
		mResult.put("errMsg",exception.getMessage());
		mResult.put("exceptionClass",exception.getClass().getSimpleName());
		response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(objectMapper.writeValueAsString(mResult));
		response.setStatus(500);
		exception.printStackTrace();
		return response;
	}

}
