package com.nucleusteq.assessmentPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception indicating that a user's email domain is invalid.
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateOptionException extends RuntimeException {

    /**
     * Constructs a new UserEmailDomainException with the specified error
     * message.
     *
     * @param message The error message.
     */
    public DuplicateOptionException(final String message) {
        super(message);
    }
}
