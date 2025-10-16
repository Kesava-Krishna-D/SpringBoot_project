package com.mallmanagement.shopping_mall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    // FIX: Added the serialVersionUID to resolve the Eclipse warning.
    private static final long serialVersionUID = 1L; // You can use 1L or the generated number.

    // Constructor to pass a specific message when the exception is thrown
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    // Optional: Include a constructor to handle the cause
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}