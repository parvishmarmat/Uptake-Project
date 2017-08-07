package com.uptake.revenue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to be thrown by REST controllers when an expected resource is not
 * found
 */
@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends BaseRuntimeException {

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
