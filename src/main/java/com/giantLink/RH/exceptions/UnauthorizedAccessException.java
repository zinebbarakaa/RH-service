package com.giantLink.RH.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@Getter
public class UnauthorizedAccessException extends RuntimeException {
    private String message;

    public UnauthorizedAccessException(String message) {
        super(message);
        this.message = message;
    }
}
