package com.giantLink.RH.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
@Getter
public class ResourceCantBeDeletedException extends RuntimeException{
    private String entity;
    private String reason;

    public ResourceCantBeDeletedException(String entity, String reason) {
        super(String.format("%s cant be deleted : %s", entity, reason));
        this.entity = entity;
        this.reason = reason;
    }
}
