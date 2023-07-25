package com.giantLink.RH.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class ResourceDuplicatedException extends RuntimeException {
    private String entity;
    private String field;
    private String value;

    public ResourceDuplicatedException(String entity, String field, String value) {
        super(String.format("%s already exists with %s:%s", entity, field, value));
        this.entity = entity;
        this.field = field;
        this.value = value;
    }
}
