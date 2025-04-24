package com.example.SpringbootEcommerce2025.config;

import com.example.SpringbootEcommerce2025.filters.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;


@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class WebSecurityConfiguration {
    private final JwtRequestFilter authFilter;

    public WebSecurityConfiguration(JwtRequestFilter authFilter) {
        this.authFilter = authFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/authenticate","/sign-up","/order/**","/api/**")
//                .permitAll()
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/**")
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/authenticate",
                        "/sign-up","/api/user/**",
                        "/order/**",
                        "/index.html", // Allow access to index.html
                        "/static/**",  // Allow access to static resources
                        "/assets/**",  // Allow access to assets (like images)
                        "/**/*.js",     // Allow access to JS files
                        "/**/*.css",    // Allow access to CSS files
                        "/**/*.ico"     // Allow access to icons
//                        "/api/product/**",
//                        "/api/category/**"
                ).permitAll() // ✅ public access
                .requestMatchers("/api/admin/**").hasRole("ADMIN") //
                .requestMatchers("/api/customer/**").hasRole("CUSTOMER")
                .anyRequest().authenticated() // everything else requires login
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
