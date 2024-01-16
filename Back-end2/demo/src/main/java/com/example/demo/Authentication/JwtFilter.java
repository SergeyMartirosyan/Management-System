package com.example.demo.Authentication;

import io.jsonwebtoken.JwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.AllArgsConstructor;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenValidationService tokenValidationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                if (tokenValidationService.validateToken(token)) {
                    Authentication auth = tokenValidationService.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    throw new JwtException("Invalid JWT token");
                }
            } catch (JwtException e) {
                throw new ServletException("Failed to validate token", e);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}
