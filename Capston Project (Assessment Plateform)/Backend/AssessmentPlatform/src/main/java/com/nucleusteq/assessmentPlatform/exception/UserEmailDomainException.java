package com.nucleusteq.assessmentPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception indicating that a user's email domain is invalid.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserEmailDomainException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UserEmailDomainException with the specified error
     * message.
     *
     * @param message The error message.
     */
    public UserEmailDomainException(final String message) {
        super(message);
    }
}
