package com.nucleusteq.assessmentPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for indicating duplicate mobile number conflicts.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateMobileNumberException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new DuplicateMobileNumberException with the specified error
     * message.
     *
     * @param message The error message.
     */
    public DuplicateMobileNumberException(final String message) {
        super(message);
    }
}
