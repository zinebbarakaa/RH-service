package com.giantLink.RH.exceptions;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String message) {
        super(message);
    }
    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
