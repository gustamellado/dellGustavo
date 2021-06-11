package com.residencia.dell.exceptions;

public class CustomExceptionSchema {
    private String message;


    protected CustomExceptionSchema() {
    }

    public CustomExceptionSchema(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
