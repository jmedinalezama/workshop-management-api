package com.workshop.management_api.domain.enums;

public enum InvoiceStatus {
  DRAFT,     // borrador
  PENDING,   // pendiente de pago
  PAID,      // pagada
  PARTIAL,   // parcialmente pagada
  OVERDUE,   // vencida
  CANCELLED  // cancelada
}
