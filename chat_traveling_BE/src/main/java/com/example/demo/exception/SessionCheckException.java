package com.example.demo.exception;

public class SessionCheckException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2593674068541678164L;
	
    public SessionCheckException(String message) {
        super(message);
    }
}
