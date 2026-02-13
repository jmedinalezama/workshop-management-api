package com.workshop.management_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.management_api.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // métodos personalizados

}
