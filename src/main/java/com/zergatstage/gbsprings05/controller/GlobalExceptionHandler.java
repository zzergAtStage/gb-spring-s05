package com.zergatstage.gbsprings05.controller;

import com.zergatstage.gbsprings05.model.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler to catch and handle exceptions thrown by controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles NoDataFoundException and returns a ResponseEntity with a 404 status code.
     *
     * @param ex The NoDataFoundException to handle.
     * @return ResponseEntity with a message and a status code.
     */
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<String> handleDataNotFoundException(NoDataFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    // TODO: Add more exception handlers for other exceptions if needed
}
