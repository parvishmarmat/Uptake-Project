package com.uptake.revenue.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception to be thrown by REST controllers when input argument(s) is not
 * proper
 */
public class BadArgumentException extends BaseRuntimeException {

    public BadArgumentException() {
    }

    public BadArgumentException(String message) {
        super(message);
        status = HttpStatus.BAD_REQUEST.value();
    }
    
    public BadArgumentException(Throwable cause) {
        super(cause);
        status = HttpStatus.BAD_REQUEST.value();
    }

    public BadArgumentException(String message, Throwable cause) {
        super(message, cause);
        status = HttpStatus.BAD_REQUEST.value();
    }

    public BadArgumentException(String code, String title, String selfUrl, String message) {
        super(code, title, selfUrl, message);
        status = HttpStatus.BAD_REQUEST.value();
    }

    public BadArgumentException(int status, String code, String title, String selfUrl) {
        super(status, code, title, selfUrl);
    }

    public BadArgumentException(int status, String code, String title, String selfUrl, String message) {
        super(status, code, title, selfUrl, message);
    }

    public BadArgumentException(int status, String code, String title, String selfUrl, Throwable cause) {
        super(status, code, title, selfUrl, cause);
    }

    public BadArgumentException(int status, String code, String title, String selfUrl, String message,
                                Throwable cause) {
        super(status, code, title, selfUrl, message, cause);
    }

}
