package com.example.demo.exception;

public class TokenCheckException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8786776313133380892L;

    public TokenCheckException(String message) {
        super(message);
    }
}
