package com.workshop.management_api.config.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtProvider jwtProvider;
  private final UserDetailsService userDetailsService;
  private final JwtAuthEntryPoint jwtAuthEntryPoint;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request, 
    HttpServletResponse response, 
    FilterChain filterChain
  ) throws ServletException, IOException {
    
    try {
      String token = tokenFromRequest(request);

      if (StringUtils.hasText(token)) {

        String username = jwtProvider.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails userDetails = userDetailsService.loadUserByUsername(username);

          if (jwtProvider.isTokenValid(token, userDetails)) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
              userDetails,
              null,
              userDetails.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
          }
        }
      }

      filterChain.doFilter(request, response);

    } catch (SignatureException | ExpiredJwtException | IllegalArgumentException ex) {

      AuthenticationException authException = new InsufficientAuthenticationException(
        ex.getMessage(), ex
      );

      jwtAuthEntryPoint.commence(request, response, authException);

    } catch(JwtException ex) {

      jwtAuthEntryPoint.commence(
        request, response, new InsufficientAuthenticationException(ex.getMessage(), ex)
      );
    
    } catch(Exception ex) {
      
      jwtAuthEntryPoint.commence(
        request, response, new InsufficientAuthenticationException(ex.getMessage(), ex)
      );
    }
  }

  private String tokenFromRequest(HttpServletRequest request) {
    String prefix = "Bearer ";
    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (StringUtils.hasText(authHeader) && authHeader.startsWith(prefix)) {
      return authHeader.substring(prefix.length());
    }

    return null;
  }

}
