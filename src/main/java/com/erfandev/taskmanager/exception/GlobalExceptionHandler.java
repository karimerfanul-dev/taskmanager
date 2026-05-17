package com.erfandev.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String,Object>>
    handleTaskNotFoundException(TaskNotFoundException ex, WebRequest request){
        Map<String,Object> map = new HashMap<>();

        map.put("timestamp", LocalDateTime.now());
        map.put("message", ex.getMessage());
        map.put("path", request.getDescription(false)
                .replace("uri=",""));

        return new  ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>>
    handleException(Exception ex, WebRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("message", "An unknown error has occurred");
        map.put("details",  ex.getMessage());

        return new  ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
