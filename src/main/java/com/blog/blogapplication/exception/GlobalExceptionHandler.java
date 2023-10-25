package com.blog.blogapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.blogapplication.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        return new ResponseEntity<ApiResponse>(new ApiResponse(ex.getMessage(), false),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ApiResponse> numberFormatExceptionHandler(){
        return new ResponseEntity<ApiResponse>(new ApiResponse("Not a valid Number", false),HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
