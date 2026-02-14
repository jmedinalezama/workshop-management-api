package com.workshop.management_api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  public Map<String, String> getCustomer() {
    return Map.of("message", "Customer data");
  }
}
