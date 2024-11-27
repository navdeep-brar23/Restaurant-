package com.restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
                .cors(cors -> {}) // Enable CORS for frontend-backend communication
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register", "/api/users/login","/api/users/forgot-password").permitAll() // Allow public access
                        .requestMatchers(HttpMethod.GET, "/api/menu").permitAll() // Allow public access to GET menu
                        .requestMatchers("/api/menu/**").hasRole("ADMIN") // Restrict menu API for admins
                        .anyRequest().authenticated() // Secure other endpoints
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // Disable HTTP Basic Authentication
                .formLogin(formLogin -> formLogin.disable()); // Disable Form-based Login

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password hashing
    }
}
