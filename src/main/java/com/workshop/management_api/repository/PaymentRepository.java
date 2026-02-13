package com.workshop.management_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.management_api.domain.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

  // métodos personalizados

}
