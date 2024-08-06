package com.quantori.auth.controller.advice;

import com.quantori.auth.exception.CustomNotFoundException;
import com.quantori.auth.exception.CustomPasswordNotCorrectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(responseBody);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(responseBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(responseBody);
    }

    @ExceptionHandler(CustomPasswordNotCorrectException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(CustomPasswordNotCorrectException ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(responseBody);
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(CustomNotFoundException ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseBody);
    }
}
