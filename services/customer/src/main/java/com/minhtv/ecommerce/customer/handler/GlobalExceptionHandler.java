package com.minhtv.ecommerce.customer.handler;

import com.minhtv.ecommerce.customer.exceptions.CustomerNotFoundException;
import com.minhtv.ecommerce.customer.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler
 * This class handles exceptions thrown by the CustomerController.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle CustomerNotFoundException
     *
     * @param ex CustomerNotFoundException
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMsg());
    }

    /**
     * Handle MethodArgumentNotValidException
     *
     * @param ex MethodArgumentNotValidException
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String,String>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(new ErrorResponse(errors));
    }
}
