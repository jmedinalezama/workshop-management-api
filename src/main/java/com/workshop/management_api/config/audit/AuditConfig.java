package com.workshop.management_api.config.audit;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.workshop.management_api.domain.entity.User;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig {

  @Bean
  AuditorAware<Long> auditorProvider() {
    return () -> {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();

      if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
        return Optional.empty();
      }

      User user = (User) auth.getPrincipal();
      
      return Optional.of(user.getId());
    };
  }

}
