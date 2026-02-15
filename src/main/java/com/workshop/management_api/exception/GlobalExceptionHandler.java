package com.workshop.management_api.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.workshop.management_api.dto.response.commom.ApiResponse;
import com.workshop.management_api.dto.response.commom.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DuplicateResourceException.class)
  public ResponseEntity<ApiResponse<ErrorResponse>> handleDuplicateResourceException(
    DuplicateResourceException ex
  ) {

    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.CONFLICT.value(),
      ex.getMessage(),
      LocalDateTime.now()
    );

    return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ApiResponse.error(errorResponse));
  }


  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ApiResponse<ErrorResponse>> handleAuthenticationException(
    AuthenticationException ex
  ) {
    
    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.UNAUTHORIZED.value(),
      ex.getMessage(),
      LocalDateTime.now()
    );

    return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse.error(errorResponse));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiResponse<ErrorResponse>> handleHttpMessageNotReadableException(
    HttpMessageNotReadableException ex
  ) {
    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.BAD_REQUEST.value(),
      "Invalid JSON format. Please check your request body.",
      LocalDateTime.now()
    );

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.error(errorResponse));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
    MethodArgumentNotValidException ex
  ) {
    
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error -> {
      errors.put(error.getField(), error.getDefaultMessage());
    });

    return ResponseEntity
              .status(HttpStatus.BAD_REQUEST.value())
              .body(ApiResponse.error(errors));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiResponse<ErrorResponse>> handleDataIntegrityViolationException(
    DataIntegrityViolationException ex
  ) {

    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.CONFLICT.value(),
      "Data integrity violation. Please check your request body.",
      LocalDateTime.now()
    );

    return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ApiResponse.error(errorResponse));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<ErrorResponse>> handleGenericException(
    Exception ex
  ) {

    ex.printStackTrace();
    
    ErrorResponse error = new ErrorResponse(
      HttpStatus.INTERNAL_SERVER_ERROR.value(),
      "An unexpected error occurred. " + ex.getMessage() + " - " + ex.getClass().getName(),
      LocalDateTime.now()
    );

    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponse.error(error));
  }

}
