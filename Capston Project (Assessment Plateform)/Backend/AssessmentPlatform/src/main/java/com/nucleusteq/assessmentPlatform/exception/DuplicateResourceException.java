package com.nucleusteq.assessmentPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class to represent a conflict due to a duplicate
 * resource in the Assessment Platform.
 */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.CONFLICT)
public class DuplicateResourceException extends RuntimeException {
    /**
     * Constructs a new DuplicateResourceException with the specified
     * detail message.
     * @param message The detail message explaining the exception.
     */
    public DuplicateResourceException(final String message) {
        super(message);
    }
}
