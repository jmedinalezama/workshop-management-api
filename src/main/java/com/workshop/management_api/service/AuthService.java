package com.workshop.management_api.service;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.management_api.config.jwt.JwtProvider;
import com.workshop.management_api.domain.entity.User;
import com.workshop.management_api.dto.request.auth.LoginRequest;
import com.workshop.management_api.dto.response.auth.LoginResponse;
import com.workshop.management_api.dto.response.auth.UserResponse;
import com.workshop.management_api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtProvider jwtProvider;

  @Transactional
  public LoginResponse login(LoginRequest request) {
    Authentication auth = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getUsername(),
        request.getPassword()
      )
    );

    User user = (User) auth.getPrincipal();

    user.setLastLogin(LocalDateTime.now());
    userRepository.save(user);

    String token = jwtProvider.generateToken(user);
    
    UserResponse userResponse = UserResponse.builder()
      .id(user.getId())
      .username(user.getUsername())
      .email(user.getEmail())
      .firstName(user.getFirstName())
      .lastName(user.getLastName())
      .fullName(user.getFullName())
      .phone(user.getPhone())
      .roleName(user.getRole().getName())
      .isActive(user.getIsActive())
      .build();

    return LoginResponse.builder()
      .token(token)
      .user(userResponse)
      .build();
  }
}
