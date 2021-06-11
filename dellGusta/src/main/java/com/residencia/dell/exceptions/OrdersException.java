package com.residencia.dell.exceptions;

public class OrdersException extends Exception {
    private static final long serialVersionUID = 1L;

    public OrdersException() {}

    public OrdersException(String message) {
        super(message);
    }
}
