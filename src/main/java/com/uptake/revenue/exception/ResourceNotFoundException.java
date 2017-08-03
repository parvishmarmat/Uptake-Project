package com.uptake.revenue.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception to be thrown by REST controllers when an expected resource is not
 * found
 */
public class ResourceNotFoundException extends BaseRuntimeException {

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
        status = HttpStatus.NOT_FOUND.value();
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
        status = HttpStatus.NOT_FOUND.value();
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
        status = HttpStatus.NOT_FOUND.value();
    }

    public ResourceNotFoundException(String code, String title, String selfUrl, String message) {
        super(code, title, selfUrl, message);
        status = HttpStatus.NOT_FOUND.value();
    }

    public ResourceNotFoundException(int status, String code, String title, String selfUrl) {
        super(status, code, title, selfUrl);
    }

    public ResourceNotFoundException(int status, String code, String title, String selfUrl, String message) {
        super(status, code, title, selfUrl, message);
    }

    public ResourceNotFoundException(int status, String code, String title, String selfUrl, Throwable cause) {
        super(status, code, title, selfUrl, cause);
    }

    public ResourceNotFoundException(int status, String code, String title, String selfUrl, String message,
                                     Throwable cause) {
        super(status, code, title, selfUrl, message, cause);
    }

}
