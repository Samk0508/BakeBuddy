package com.bakebuddy.config;

import java.util.Arrays;
import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
@Configuration
@EnableWebSecurity
public class AppConfig {

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        // Admin-specific routes
//                        .requestMatchers("/api/**").hasRole("ADMIN")
//
//                        // Bakery Owner-specific routes
//                        .requestMatchers("/api/bakery/**").hasRole("BAKERY_OWNER")
//
//                        // Customer-specific routes (Authenticated Users)
//                        .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
//                        .requestMatchers("/api/orders/**").hasRole("CUSTOMER")
//
//                        // Public routes
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .addFilterBefore(new JwtTokenValidator(),BasicAuthenticationFilter.class)
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()));
//
//        return http.build();
//    }
//
//    // CORS Configuration
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        return request -> {
//            CorsConfiguration config = new CorsConfiguration();
//            config.setAllowedOrigins(Arrays.asList(
//                "https://bakebuddy.com",
//                "http://localhost:3000",
//                "http://localhost:8080"
//            ));
//            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//            config.setAllowedHeaders(Collections.singletonList("*"));
//            config.setExposedHeaders(Arrays.asList("Authorization"));
//            config.setAllowCredentials(true);
//            config.setMaxAge(3600L);
//            return config;
//        };
//    }
//
   @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
