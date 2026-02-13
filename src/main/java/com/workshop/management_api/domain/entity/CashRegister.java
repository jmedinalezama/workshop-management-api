package com.workshop.management_api.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
@Table(name = "cash_registers")
public class CashRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "opening_date", nullable = false)
    private LocalDateTime openingDate;

    @Column(name = "closing_date")
    private LocalDateTime closingDate;

    @Column(name = "opening_balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal openingBalance;

    @Column(name = "closing_balance", precision = 10, scale = 2)
    private BigDecimal closingBalance;

    @Column(name = "expected_balance", precision = 10, scale = 2)
    private BigDecimal expectedBalance;

    @Column(name = "difference", precision = 10, scale = 2)
    private BigDecimal difference;

    @Column(name = "is_open", nullable = false)
    @Builder.Default
    private Boolean isOpen = true;

    @Column(name = "opened_by")
    private Long openedBy;

    @Column(name = "closed_by")
    private Long closedBy;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @PrePersist
    protected void onCreate() {
        if (openingDate == null) {
            openingDate = LocalDateTime.now();
        }
    }
}
