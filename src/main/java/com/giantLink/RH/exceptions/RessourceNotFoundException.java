package com.giantLink.RH.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class RessourceNotFoundException extends RuntimeException {
    private String entity;
    private String field;
    private String value;

    public RessourceNotFoundException(String message){
        super(message);
    }
    public RessourceNotFoundException(String entity, String field, String value) {
        super(String.format("%s Not Found with %s:%s", entity, field, value));
        this.entity = entity;
        this.field = field;
        this.value = value;
    }
}


