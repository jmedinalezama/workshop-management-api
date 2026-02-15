package com.workshop.management_api.mapper;

import org.springframework.stereotype.Component;

import com.workshop.management_api.domain.entity.Customer;
import com.workshop.management_api.dto.request.customer.CustomerRequest;
import com.workshop.management_api.dto.response.customer.CustomerResponse;

@Component
public class CustomerMapper {

  public Customer toEntity(CustomerRequest request) {
    return Customer.builder()
      .customerType(request.getCustomerType())
      .documentType(request.getDocumentType())
      .documentNumber(request.getDocumentNumber())
      .firstName(request.getFirstName())
      .lastName(request.getLastName())
      .companyName(request.getCompanyName())
      .email(request.getEmail())
      .phone(request.getPhone())
      .secondaryPhone(request.getSecondaryPhone())
      .address(request.getAddress())
      .city(request.getCity())
      .state(request.getState())
      .postalCode(request.getPostalCode())
      .notes(request.getNotes())
      .isActive(true)
      .build();
  }

  public CustomerResponse toResponse(Customer customer) {
    return CustomerResponse.builder()
      .id(customer.getId())
      .customerType(customer.getCustomerType())
      .documentType(customer.getDocumentType())
      .documentNumber(customer.getDocumentNumber())
      .firstName(customer.getFirstName())
      .lastName(customer.getLastName())
      .companyName(customer.getCompanyName())
      .fullName(customer.getFullName())
      .email(customer.getEmail())
      .phone(customer.getPhone())
      .secondaryPhone(customer.getSecondaryPhone())
      .address(customer.getAddress())
      .city(customer.getCity())
      .state(customer.getState())
      .postalCode(customer.getPostalCode())
      .isActive(customer.getIsActive())
      .notes(customer.getNotes())
      .createdAt(customer.getCreatedAt())
      .updatedAt(customer.getUpdatedAt())
      .build();
  }

}
