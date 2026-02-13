package com.workshop.management_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.management_api.domain.entity.PartCategory;

@Repository
public interface PartCategoryRepository extends JpaRepository<PartCategory, Long> {

  // métodos personalizados

}
