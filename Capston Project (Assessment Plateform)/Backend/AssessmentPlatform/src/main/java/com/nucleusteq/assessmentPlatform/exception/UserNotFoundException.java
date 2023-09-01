package com.nucleusteq.assessmentPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception indicating that a user is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UserNotFoundException with the specified error message.
     *
     * @param message The error message.
     */
    public UserNotFoundException(final String message) {
        super(message);
    }

}
