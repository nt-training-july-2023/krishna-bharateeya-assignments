package com.nucleusteq.assessmentPlatform.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for indicating not found conflicts.
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified error
     * message.
     *
     * @param message The error message.
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
