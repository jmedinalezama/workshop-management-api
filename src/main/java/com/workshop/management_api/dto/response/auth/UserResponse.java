package com.workshop.management_api.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

  private Long id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private String fullName;
  private String phone;
  private String roleName;
  private Boolean isActive;
  
}
