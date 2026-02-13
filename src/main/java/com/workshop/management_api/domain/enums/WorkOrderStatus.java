package com.workshop.management_api.domain.enums;

public enum WorkOrderStatus {
  RECEIVED,
  IN_DIAGNOSIS,
  WAITING_FOR_APPROVAL,
  APPROVED,
  IN_PROGRESS,
  COMPLETED,
  INVOICED,
  DELIVERED,
  CANCELLED
}
