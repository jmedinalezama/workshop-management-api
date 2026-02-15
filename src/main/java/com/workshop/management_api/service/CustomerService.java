package com.workshop.management_api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.management_api.domain.entity.Customer;
import com.workshop.management_api.dto.request.customer.CustomerRequest;
import com.workshop.management_api.dto.response.customer.CustomerResponse;
import com.workshop.management_api.exception.DuplicateResourceException;
import com.workshop.management_api.mapper.CustomerMapper;
import com.workshop.management_api.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  @Transactional
  public CustomerResponse createCustomer(CustomerRequest request) {

    // validar si existe por el numero de documento
    if (customerRepository.existsByDocumentNumber(request.getDocumentNumber())) {
      throw new DuplicateResourceException(
        "Customer already exists with document number: " + request.getDocumentNumber()
      );
    }

    // validar si existe por email
    if (customerRepository.existsByEmail(request.getEmail())) {
      throw new DuplicateResourceException(
        "Customer already exists with email: " + request.getEmail()
      );
    }
    
    Customer customer = customerMapper.toEntity(request);
    Customer savedCustomer = customerRepository.save(customer);
    return customerMapper.toResponse(savedCustomer);
  }

}
