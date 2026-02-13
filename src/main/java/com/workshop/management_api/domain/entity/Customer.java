package com.workshop.management_api.domain.entity;

import com.workshop.management_api.domain.audit.Auditable;
import com.workshop.management_api.domain.enums.CustomerType;
import com.workshop.management_api.domain.enums.DocumentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "customers")
public class Customer extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "customer_type", nullable = false, length = 20)
  private CustomerType customerType;

  @Enumerated(EnumType.STRING)
  @Column(name = "document_type", nullable = false, length = 20)
  private DocumentType documentType; 

  @Column(name = "document_number", nullable = false, unique = true, length = 20)
  private String documentNumber;

  @Column(name = "first_name", length = 100)
  private String firstName;

  @Column(name = "last_name", length = 100)
  private String lastName;

  @Column(name = "company_name", length = 200)
  private String companyName;

  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @Column(nullable = false, length = 20)
  private String phone;

  @Column(name = "secondary_phone", length = 20)
  private String secondaryPhone;

  @Column(columnDefinition = "TEXT")
  private String address;

  @Column(length = 100)
  private String city;

  @Column(length = 100)
  private String state;

  @Column(name = "postal_code", length = 10)
  private String postalCode;

  @Column(name = "is_active", nullable = false)
  @Builder.Default
  private Boolean isActive = true;

  @Column(columnDefinition = "TEXT")
  private String notes;

  public String getFullName() {
    if (customerType == CustomerType.BUSINESS) {
      return companyName;
    }

    return firstName.concat(" ").concat(lastName);
  }

}
