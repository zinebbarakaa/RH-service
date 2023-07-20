package com.giantLink.RH.exceptions;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {//Classe personnalisée pour représenter les détails d'une erreur de validation 
    private int errorCode;
    private String errorMessage;
    private Map<String, String> validationErrors;

    
}