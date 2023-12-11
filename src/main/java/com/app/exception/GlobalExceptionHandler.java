package com.app.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(MethodArgumentNotValidException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) 
{
	return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
   
}

@ExceptionHandler(IllegalArgumentException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) 
{
   return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
}
}

