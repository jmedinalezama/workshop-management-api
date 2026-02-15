package com.workshop.management_api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.management_api.domain.entity.Customer;
import com.workshop.management_api.dto.request.customer.CustomerRequest;
import com.workshop.management_api.dto.response.customer.CustomerResponse;
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

    Customer customer = customerMapper.toEntity(request);
    Customer savedCustomer = customerRepository.save(customer);
    return customerMapper.toResponse(savedCustomer);
  }

}
