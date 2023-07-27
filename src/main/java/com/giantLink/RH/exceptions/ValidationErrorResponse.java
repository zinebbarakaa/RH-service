package com.giantLink.RH.exceptions;

import java.util.Map;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {//Classe personnalisée pour représenter les détails d'une erreur de validation 
    private int errorCode;
    private String errorMessage;
    private Map<String, String> validationErrors;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}