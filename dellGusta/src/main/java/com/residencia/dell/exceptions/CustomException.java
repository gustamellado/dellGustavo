package com.residencia.dell.exceptions;

public class CustomException extends RuntimeException {
    private String message;


    protected CustomException() {}

    public CustomException(String message) {
        this.message = message;

    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
