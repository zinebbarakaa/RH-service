package com.giantLink.RH.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class InvalidInputException extends RuntimeException {
    private String message;

    public InvalidInputException(String message) {
        super(message);
        this.message = message;
    }
}

