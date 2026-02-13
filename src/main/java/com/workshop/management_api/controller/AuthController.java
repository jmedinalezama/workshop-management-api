package com.workshop.management_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.management_api.dto.request.auth.LoginRequest;
import com.workshop.management_api.dto.response.auth.LoginResponse;
import com.workshop.management_api.dto.response.commom.ApiResponse;
import com.workshop.management_api.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<LoginResponse>> login(
    @Valid @RequestBody LoginRequest request
  ) {

    LoginResponse response = authService.login(request);

    return ResponseEntity.ok(
      ApiResponse.success("Login successful", response)
    );
  }

}
