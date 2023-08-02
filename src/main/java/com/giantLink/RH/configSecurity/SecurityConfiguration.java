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
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers("api/v1/auth/**").permitAll()
                        .requestMatchers(GET,"api/v1/employees/**").hasAnyAuthority("READ")
                        .requestMatchers(POST, "api/v1/employees/**").hasAnyAuthority("CREATE")
                        .requestMatchers(PUT,"api/v1/employees/**").hasAnyAuthority("UPDATE")
                        .requestMatchers(DELETE, "api/v1/employees/**").hasAnyAuthority("DELETE")
                        .requestMatchers("api/v1/employees").hasAnyRole("ADMIN_RH","MANAGER_RH","EMPLOYEE")
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider)
              .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(tokenAuthenticationException))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
