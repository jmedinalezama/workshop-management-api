package com.workshop.management_api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.workshop.management_api.domain.audit.Auditable;

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
@Table(name = "parts")
public class Part extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false, length = 50)
  private String code;

  @Column(name = "oem_code", length = 50)
  private String oemCode;

  @Column(nullable = false, length = 200)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(length = 100)
  private String brand;

  @Column(name = "unit_of_measure", length = 20)
  @Builder.Default
  private String unitOfMeasure = "UNIT";

  @Column(length = 50)
  private String location;

  @Column(name = "purchase_price", nullable = false, precision = 10, scale = 2)
  private BigDecimal purchasePrice;

  @Column(name = "sale_price", nullable = false, precision = 10, scale = 2)
  private BigDecimal salePrice;

  @Column(name = "profit_margin", precision = 5, scale = 2)
  private BigDecimal profitMargin;

  @Column(name = "current_stock", precision = 10, scale = 2)
  @Builder.Default
  private BigDecimal currentStock = BigDecimal.ZERO;

  @Column(name = "minimum_stock", precision = 10, scale = 2)
  @Builder.Default
  private BigDecimal minimumStock = BigDecimal.ZERO;

  @Column(name = "maximum_stock", precision = 10, scale = 2)
  private BigDecimal maximumStock;

  @Column(name = "is_active", nullable = false)
  @Builder.Default
  private Boolean isActive = true;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private PartCategory category;

  // formula: ((salePrice - purchasePrice) / purchasePrice) * 100
  @PrePersist
  @PreUpdate
  private void calculateProfitMargin() {
    if (purchasePrice != null && salePrice != null && purchasePrice.compareTo(BigDecimal.ZERO) > 0) {
      this.profitMargin = salePrice.subtract(purchasePrice)
          .divide(purchasePrice, 4, RoundingMode.HALF_UP)
          .multiply(BigDecimal.valueOf(100))
          .setScale(2, RoundingMode.HALF_UP);
    }
  }

}
