package com.workshop.management_api.domain.entity;

import com.workshop.management_api.domain.audit.Auditable;
import com.workshop.management_api.domain.enums.FuelType;
import com.workshop.management_api.domain.enums.TransmissionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "vehicles")
public class Vehicle extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "license_plate", nullable = false, unique = true, length = 20)
  private String licensePlate;

  @Column(nullable = false, length = 50)
  private String brand;

  @Column(nullable = false, length = 50)
  private String model;

  @Column(length = 4)
  private Integer year;

  @Column(length = 50)
  private String color;

  @Column(name = "vin_number", length = 17, unique = true)
  private String vinNumber;

  @Enumerated(EnumType.STRING)
  @Column(name = "fuel_type", length = 20)
  private FuelType fuelType;

  @Enumerated(EnumType.STRING)
  @Column(name = "transmission_type", length = 20)
  private TransmissionType transmissionType;

  @Column(name = "engine_size", length = 20)
  private String engineSize;

  @Column(name = "current_mileage")
  private Integer currentMileage;

  @Column(name = "is_active", nullable = false)
  @Builder.Default
  private Boolean isActive = true;

  @Column(columnDefinition = "TEXT")
  private String notes;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  public String getVehicleInfo() {
    return String.format("%s %s %s (%s)", brand, model, year, licensePlate);
  }


}
