package com.nucleusteq.assessmentPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for indicating login failed due to unauthorized access.
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginFailedException extends RuntimeException {

    /**
     * Constructs a new LoginFailedException with the specified error message.
     *
     * @param message The error message.
     */
    public LoginFailedException(final String message) {
        super(message);
    }
}
