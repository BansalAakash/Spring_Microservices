package com.microservices.userservice.exceptions;

import com.microservices.userservice.payload.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    //Now whenever ResourceNotFoundException is generated, this method will be called
    public ResponseEntity<APIResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        String message = exception.getMessage();
        APIResponse apiResponse = APIResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        //The above is called builder pattern. It helps to create the object of a class by passing the values.
        //It is enabled by doing @Builder in the entity class of APIResponse.
        // (Available because of lombok)
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
