package com.workshop.management_api.dto.request.customer;

import com.workshop.management_api.domain.enums.CustomerType;
import com.workshop.management_api.domain.enums.DocumentType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

  @NotNull(message = "Customer type is required")
  private CustomerType customerType;

  @NotNull(message = "Document type is required")
  private DocumentType documentType;

  @NotBlank(message = "Document number is required")
  private String documentNumber;

  private String firstName;

  private String lastName;

  private String companyName;

  @NotBlank(message = "Email is required")
  @Email(message = "Email must be valid")
  private String email;

  @NotBlank(message = "Phone is required")
  private String phone;

  private String secondaryPhone;

  private String address;

  private String city;

  private String state;

  private String postalCode;

  private String notes;

}
