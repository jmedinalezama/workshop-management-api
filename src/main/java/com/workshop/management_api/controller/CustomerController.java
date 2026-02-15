package com.workshop.management_api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.management_api.dto.request.customer.CustomerRequest;
import com.workshop.management_api.dto.response.commom.ApiResponse;
import com.workshop.management_api.dto.response.customer.CustomerResponse;
import com.workshop.management_api.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public Map<String, String> getCustomer() {
    return Map.of("message", "Customer data");
  }

  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(
    @Valid @RequestBody CustomerRequest request
  ) {
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
              ApiResponse.success(
                "Customer created successfully", 
                customerService.createCustomer(request)
              )
            );
  }
}
