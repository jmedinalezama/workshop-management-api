package com.workshop.management_api.domain.enums;

public enum MovementType {
  PURCHASE,           // Entrada por compra
  SALE,               // Salida por venta/uso
  ADJUSTMENT,         // Ajuste de inventario
  RETURN_CUSTOMER,    // Devolución de cliente
  RETURN_SUPPLIER,    // Devolución a proveedor
  LOSS                // Merma/Pérdida  
}
