package com.workshop.management_api.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.workshop.management_api.domain.audit.Auditable;
import com.workshop.management_api.domain.enums.WorkOrderStatus;

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
@Table(name = "work_orders")
public class WorkOrder extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "order_number", nullable = false, unique = true, length = 20)
  private String orderNumber;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private WorkOrderStatus status;

  @Column(name = "reception_date", nullable = false)
  private LocalDateTime receptionDate;

  @Column(name = "promised_date")
  private LocalDateTime promisedDate;

  @Column(name = "completion_date")
  private LocalDateTime completionDate;

  @Column(name = "delivery_date")
  private LocalDateTime deliveryDate;

  @Column(name = "mileage_at_reception")
  private Integer mileageAtReception;

  @Column(name = "fuel_level", length = 20)
  private String fuelLevel;

  @Column(name = "customer_complaint", columnDefinition = "TEXT")
  private String customerComplaint;

  @Column(name = "initial_diagnosis", columnDefinition = "TEXT")
  private String initialDiagnosis;

  @Column(name = "work_performed", columnDefinition = "TEXT")
  private String workPerformed;

  @Column(name = "estimated_cost", precision = 10, scale = 2)
  private BigDecimal estimatedCost;

  @Column(name = "final_cost", precision = 10, scale = 2)
  private BigDecimal finalCost;

  @Column(name = "is_approved", nullable = false)
  @Builder.Default
  private Boolean isApproved = false;

  @Column(name = "approval_date")
  private LocalDateTime approvalDate;

  @Column(name = "priority_level")
  @Builder.Default
  private Integer priorityLevel = 1;

  @Column(columnDefinition = "TEXT")
  private String notes;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "vehicle_id", nullable = false)
  private Vehicle vehicle;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "advisor_id", nullable = false)
  private User advisor;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "assigned_mechanic_id")
  private User assignedMechanic;

  public String getDisplayNumber() {
    return "OT-".concat(orderNumber);
  }

  public boolean isCompleted() {
    return status == WorkOrderStatus.COMPLETED ||
           status == WorkOrderStatus.INVOICED ||
           status == WorkOrderStatus.DELIVERED;
  }

  public boolean canBeModified() {
    return status != WorkOrderStatus.DELIVERED && 
           status != WorkOrderStatus.CANCELLED;
  }

}
