package com.quantori.auth.exception;

public class CustomPasswordNotCorrectException extends RuntimeException{
    public CustomPasswordNotCorrectException() {
    }

    public CustomPasswordNotCorrectException(String message) {
        super(message);
    }

    public CustomPasswordNotCorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomPasswordNotCorrectException(Throwable cause) {
        super(cause);
    }

    public CustomPasswordNotCorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
