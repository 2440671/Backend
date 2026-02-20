package org.genc.sneakoapp.AdminService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalRestAPIExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFound(ProductNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Product Not Found", ex.getMessage());
    }

    @ExceptionHandler(ProductCreateException.class)
    public ResponseEntity<Object> handleProductCreate(ProductCreateException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Product Creation Failed", ex.getMessage());
    }

    @ExceptionHandler(ProductUpdateException.class)
    public ResponseEntity<Object> handleProductUpdate(ProductUpdateException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Product Update Failed", ex.getMessage());
    }

    @ExceptionHandler(ProductDeleteException.class)
    public ResponseEntity<Object> handleProductDelete(ProductDeleteException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Product Deletion Failed", ex.getMessage());
    }

    @ExceptionHandler(OrderUpdateException.class)
    public ResponseEntity<Object> handleOrderUpdate(OrderUpdateException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Order Update Failed", ex.getMessage());
    }

    @ExceptionHandler(UserDeleteException.class)
    public ResponseEntity<Object> handleUserDelete(UserDeleteException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "User Deletion Failed", ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "User Not Found", ex.getMessage());
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String error, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }


    /**
     * Generic exception handler for all other unexpected errors.
     * Returns an HTTP 500 Internal Server Error response.
     * NOTE: This should be defined last, as Spring picks the most specific handler first.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "An unexpected error occurred. Please try again later.");
        // In a real app, you would log the full error here for system monitoring: log.error("Unhandled Exception", ex);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}