package com.zergatstage.gbsprings05.controller;

import com.zergatstage.gbsprings05.model.TasksNoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TasksNoDataFoundException.class)
    public ResponseEntity<String> handleDataNotFoundException(TasksNoDataFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    // TODO: Add more exception handlers for other exceptions if needed
}