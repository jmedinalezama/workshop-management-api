package com.workshop.management_api.domain.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.workshop.management_api.domain.audit.Auditable;
import com.workshop.management_api.domain.enums.InvoiceStatus;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@Table(name = "invoices")
public class Invoice extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false, length = 20)
  private String invoiceNumber;

  @Column(name = "issue_date", nullable = false)
  private LocalDate issueDate;

  @Column(name = "due_date")
  private LocalDate dueDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private InvoiceStatus status;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal subtotal;

  @Column(name = "tax_rate", precision = 5, scale = 2)
  private BigDecimal taxRate;

  @Column(name = "tax_amount", precision = 10, scale = 2)
  private BigDecimal taxAmount;

  @Column(name = "discount_amount", precision = 10, scale = 2)
  @Builder.Default
  private BigDecimal discountAmount = BigDecimal.ZERO;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal total;

  @Column(name = "paid_amount", precision = 10, scale = 2)
  @Builder.Default
  private BigDecimal paidAmount = BigDecimal.ZERO;

  @Column(name = "balance_due", precision = 10, scale = 2)
  private BigDecimal balanceDue;

  @Column(columnDefinition = "TEXT")
  private String notes;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "work_order_id")
  private WorkOrder workOrder;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<Payment> payments = new ArrayList<>();

  @PrePersist
  @PreUpdate
  private void calculateTotals() {
    if (taxRate != null && subtotal != null) {
      this.taxAmount = subtotal.multiply(taxRate)
        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    } else {
      this.taxAmount = BigDecimal.ZERO;
    }

    if (discountAmount == null) {
      this.discountAmount = BigDecimal.ZERO;
    }

    this.total = subtotal.add(taxAmount).subtract(discountAmount);
    this.balanceDue = total.subtract(paidAmount != null ? paidAmount : BigDecimal.ZERO);
  }

}