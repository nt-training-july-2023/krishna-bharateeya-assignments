package com.nucleusteq.assessmentPlatform.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Custom exception for indicating duplicate email conflicts.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmailException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new DuplicateEmailException with the specified error
     * message.
     *
     * @param message The error message.
     */
    public DuplicateEmailException(final String message) {
        super(message);
    }
}
