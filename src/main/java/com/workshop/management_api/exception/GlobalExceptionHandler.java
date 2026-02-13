package com.workshop.management_api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.workshop.management_api.dto.response.commom.ApiResponse;
import com.workshop.management_api.dto.response.commom.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

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

}
