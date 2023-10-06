package com.nucleusteq.assessmentPlatform.utility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents an error response for all exceptions.
 */
@Setter
@Getter
@NoArgsConstructor
public class ErrorResponse {

    /**
     * The HTTP status code indicating the type of error.
     */
    private int statusCode;

    /**
     * An optional error message providing additional information about the
     * error.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * A map of field-level validation errors, where the keys represent field
     * names and the values are error messages specific to those fields.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;

    /**
     * Constructs an ErrorResponse with the specified status code and
     * field-level errors.
     * @param stasCode The HTTP status code indicating the type of error.
     * @param errs A map of field-level validation errors.
     */
    public ErrorResponse(final int stasCode, final Map<String, String> errs) {
        this.statusCode = stasCode;
        this.errors = errs != null ? new HashMap<>(errs) : null;
    }

    /**
     * Constructs an ErrorResponse with the specified status code and error
     * message.
     * @param stasCode The HTTP status code indicating the type of error.
     * @param msg    An optional error message providing additional
     *                   information about the error.
     */
    public ErrorResponse(final int stasCode, final String msg) {
        this.statusCode = stasCode;
        this.message = msg;
    }

    /**
     *  Getter for field error.
     * @return map containing errors.
     */
    public Map<String, String> getErrors() {
        return errors != null ? Collections.unmodifiableMap(errors) : null;
    }

   /**
    * Setter for field error.
    * @param inputErrors as parameter.
    */
    public void setErrors(final Map<String, String> inputErrors) {
        this.errors = inputErrors != null ? new HashMap<>(inputErrors) : null;
    }

    /**
     * All argument constructor of error fields.
     * @param status as a parameter.
     * @param msg as a parameter.
     * @param inputErrors as a parameter.
     */
    public ErrorResponse(final int status, final String msg,
            final Map<String, String> inputErrors) {
        super();
        this.statusCode = status;
        this.message = msg;
        this.errors = inputErrors != null ? new HashMap<>(inputErrors) : null;
    }
}
