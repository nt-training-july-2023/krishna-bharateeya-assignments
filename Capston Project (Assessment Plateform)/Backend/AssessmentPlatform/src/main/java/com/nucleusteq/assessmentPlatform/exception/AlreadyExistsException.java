package com.nucleusteq.assessmentPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new AlreadyExistsException with the specified error message.
     *
     * @param message The error message.
     */
    public AlreadyExistsException(final String message) {
        super(message);
    }
}
