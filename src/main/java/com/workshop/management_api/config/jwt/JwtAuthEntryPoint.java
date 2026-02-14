package com.workshop.management_api.config.jwt;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.workshop.management_api.dto.response.commom.ApiResponse;
import com.workshop.management_api.dto.response.commom.ErrorResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(
    HttpServletRequest request, 
    HttpServletResponse response, 
    AuthenticationException authException
  ) throws IOException, ServletException {

    Throwable cause = authException.getCause();
   
    String message = switch(cause) {
      case SignatureException signatureException -> "Invalid token. Invalid signature.";
      case ExpiredJwtException expiredJwtException -> "Token is expired. Please login again.";
      case JwtException jwtException -> "Invalid token.";
      default -> "Unauthorized. Please login again.";
    };

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    errorResponse.setMessage(message);
    errorResponse.setTimestamp(LocalDateTime.now());

    ApiResponse<ErrorResponse> apiResponse = ApiResponse.error(errorResponse);
    
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    String jsonResponse = mapper.writeValueAsString(apiResponse);

    response.setContentType("application/json");
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.getWriter().write(jsonResponse);

  }
}
