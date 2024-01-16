package com.example.demo.Authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenValidationService tokenValidationService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/v1/login").permitAll() // Allow unauthenticated access to /api/v1/login
                .anyRequest().authenticated() // Require authentication for all other endpoints
            )
            .addFilterBefore(new JwtFilter(tokenValidationService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
