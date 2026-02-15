package com.workshop.management_api.dto.response.customer;

import java.time.LocalDateTime;

import com.workshop.management_api.domain.enums.CustomerType;
import com.workshop.management_api.domain.enums.DocumentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

  private Long id;
  private CustomerType customerType;
  private DocumentType documentType;
  private String documentNumber;
  private String firstName;
  private String lastName;
  private String companyName;
  private String fullName;
  private String email;
  private String phone;
  private String secondaryPhone;
  private String address;
  private String city;
  private String state;
  private String postalCode;
  private Boolean isActive;
  private String notes;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
