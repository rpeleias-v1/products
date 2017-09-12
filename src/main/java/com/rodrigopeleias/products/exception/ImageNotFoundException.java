package com.rodrigopeleias.products.exception;

public class ImageNotFoundException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Image informed not found: ";

    public ImageNotFoundException(String msg) {
        super(EXCEPTION_MESSAGE  + msg);
    }

    public ImageNotFoundException(String msg, Throwable cause) {
        super(EXCEPTION_MESSAGE + msg, cause);
    }
}
