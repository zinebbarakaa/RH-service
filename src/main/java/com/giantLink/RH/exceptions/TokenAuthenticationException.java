package com.giantLink.RH.exceptions;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.giantLink.RH.models.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Component
public class TokenAuthenticationException  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorResponse errorDetails = ErrorResponse.builder()
                .errorCode(HttpStatus.UNAUTHORIZED.value())
                .errorMessage(authException.getMessage())
                .errorDetails("Unauthorized")
                .build();
        ObjectMapper objectMapper=new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        objectMapper.writeValue(response.getOutputStream(),errorDetails);
    }
}

