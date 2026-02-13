package com.workshop.management_api.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

  private String token;

  @Builder.Default
  private String type = "Bearer";
  
  private UserResponse user;

}
