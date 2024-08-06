package com.quantori.auth.exception;

public class CustomTokenNotValidException extends RuntimeException{
    public CustomTokenNotValidException() {
    }

    public CustomTokenNotValidException(String message) {
        super(message);
    }

    public CustomTokenNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomTokenNotValidException(Throwable cause) {
        super(cause);
    }

    public CustomTokenNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
