package com.microservices.hotelservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Hotel resource not found!");
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
