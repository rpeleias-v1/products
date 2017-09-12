package com.rodrigopeleias.products.exception;

public class ProductNotFoundException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Product informed not found: ";

    public ProductNotFoundException(String msg) {
        super(EXCEPTION_MESSAGE  + msg);
    }

    public ProductNotFoundException(String msg, Throwable cause) {
        super(EXCEPTION_MESSAGE + msg, cause);
    }
}
