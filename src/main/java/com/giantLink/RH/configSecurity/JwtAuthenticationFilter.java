package com.giantLink.RH.configSecurity;

import com.giantLink.RH.services.impl.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    // This method is called for each incoming request
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException, ExpiredJwtException {

        // Get the Authorization header from the request
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // If the Authorization header is missing or doesn't start with "Bearer ", proceed to the next filter
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT from the Authorization header
        jwt = authHeader.substring(7);

        // Extract the username from the JWT
        username = jwtService.extractUsername(jwt);

        // If the username is not null and no user is currently authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load the user details from the userDetailsService
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Create an authentication token and set it in the security context
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            // Check if the JWT is valid for the user

        }

        // Proceed to the next filter in the chain
        filterChain.doFilter(request, response);
    }
}

