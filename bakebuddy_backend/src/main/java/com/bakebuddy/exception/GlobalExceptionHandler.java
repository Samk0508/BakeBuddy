package com.bakebuddy.exception;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle UserException
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> handleUserException(UserException ex, WebRequest req) {
        ErrorDetails error = new ErrorDetails(
                ex.getMessage(), req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle ProductException
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorDetails> handleProductException(ProductException ex, WebRequest req) {
        ErrorDetails error = new ErrorDetails(
                ex.getMessage(), req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle CartItemException
    @ExceptionHandler(CartItemException.class)
    public ResponseEntity<ErrorDetails> handleCartItemException(CartItemException ex, WebRequest req) {
        ErrorDetails error = new ErrorDetails(
                ex.getMessage(), req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle OrderException
    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ErrorDetails> handleOrderException(OrderException ex, WebRequest req) {
        ErrorDetails error = new ErrorDetails(
                ex.getMessage(), req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle BakeryOwnerException
    @ExceptionHandler(BakeryOwnerException.class)
    public ResponseEntity<ErrorDetails> handleSellerException(BakeryOwnerException ex, WebRequest req) {
        ErrorDetails error = new ErrorDetails(
                ex.getMessage(), req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle CouponNotValidException
    @ExceptionHandler(CouponNotValidException.class)
    public ResponseEntity<ErrorDetails> handleCouponNotValidException(CouponNotValidException ex, WebRequest req) {
        ErrorDetails error = new ErrorDetails(
                ex.getMessage(), req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle Validation Errors (MethodArgumentNotValidException)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message", "Validation failed");

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle NoHandlerFoundException
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message", "Endpoint not found");
        response.put("path", ex.getRequestURL());
        
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle Global Exception (Fallback for All Unhandled Exceptions)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest req) {
        ErrorDetails error = new ErrorDetails(
                ex.getMessage(), req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
