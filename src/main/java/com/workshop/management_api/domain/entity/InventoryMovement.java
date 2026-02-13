package com.workshop.management_api.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.workshop.management_api.domain.enums.MovementType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "inventory_movements")
public class InventoryMovement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "movement_type", nullable = false, length = 20)
  private MovementType movementType;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal quantity;

  @Column(name = "unit_cost", precision = 10, scale = 2)
  private BigDecimal unitCost;

  @Column(name = "reference_type", length = 50)
  private String referenceType;

  @Column(name = "reference_id")
  private Long referenceId;

  @Column(name = "previous_stock", precision = 10, scale = 2)
  private BigDecimal previousStock;

  @Column(name = "new_stock", precision = 10, scale = 2)
  private BigDecimal newStock;

  @Column(columnDefinition = "TEXT")
  private String notes;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "created_by")
  private Long createdBy;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "part_id", nullable = false)
  private Part part;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }
}
