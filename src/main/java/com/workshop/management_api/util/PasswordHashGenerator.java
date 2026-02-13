package com.workshop.management_api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility class to generate BCrypt password hashes
 * Run this class to generate a hash for the admin password
 */
public class PasswordHashGenerator {

  public static void main(String[] args) {
    
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    String password = "admin";
    String hash = encoder.encode(password);

    System.out.println("Password: " + password);
    System.out.println("Hash: " + hash);

  }

}
