package com.workshop.management_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.management_api.domain.entity.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

  // métodos personalizados

}
