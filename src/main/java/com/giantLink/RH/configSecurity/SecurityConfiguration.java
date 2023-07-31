package com.giantLink.RH.configSecurity;
import com.giantLink.RH.exceptions.TokenAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final TokenAuthenticationException tokenAuthenticationException;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Disable CSRF protection as we are using JWT and have no session management
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                // Set the session creation policy to STATELESS as we are not using sessions
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Define authorization rules for specific HTTP requests
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        // Allow unauthenticated access to API endpoints related to authentication
                        .requestMatchers("api/v1/auth/**").permitAll()
                        // Allow access to GET API endpoints related to employees only for users with "ADMIN_READ" authority
                        .requestMatchers(GET,"api/v1/employees/**").hasAnyAuthority("ADMIN_READ")
                        // Allow access to POST API endpoints related to employees only for users with "ADMIN_CREATE" authority
                        .requestMatchers(POST, "api/v1/employees/**").hasAnyAuthority("ADMIN_CREATE")
                        // Allow access to PUT API endpoints related to employees only for users with "ADMIN_UPDATE" authority
                        .requestMatchers(PUT,"api/v1/employees/**").hasAnyAuthority("ADMIN_UPDATE")
                        // Allow access to DELETE API endpoints related to employees only for users with "ADMIN_DELETE" authority
                        .requestMatchers(DELETE, "api/v1/employees/**").hasAnyAuthority("ADMIN_DELETE")
                        // Allow access to the "/api/v1/employees" endpoint for users with roles "ADMIN_RH" or "MANAGER_RH"
                        .requestMatchers("api/v1/employees").hasAnyRole("ADMIN_RH","MANAGER_RH")
                        // For all other requests, user must be authenticated
                        .anyRequest().authenticated())
                // Configure the custom authentication provider
                .authenticationProvider(authenticationProvider)
                // Configure the custom token authentication exception handler
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(tokenAuthenticationException))
                // Add the JWT authentication filter before the UsernamePasswordAuthenticationFilter in the filter chain
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
