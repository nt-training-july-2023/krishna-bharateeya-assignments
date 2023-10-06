package com.nucleusteq.assessmentPlatform.utility;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a success response with an HTTP status code and a
 * message.
 */
@Setter
@Getter
public class SuccessResponse {
    /**
     * The HTTP status code indicating the success or status of the response.
     */
    private int statusCode;

    /**
     * A message providing additional information about the success of the
     * operation.
     */
    private String message;

    /**
     * Constructs a new SuccessResponse with the specified HTTP status code and
     * message.
     *
     * @param sttsCode The HTTP status code indicating the success or status
     *                   of the response.
     * @param msge    A message providing additional information about the
     *                   success of the operation.
     */
    public SuccessResponse(final int sttsCode, final String msge) {
        this.statusCode = sttsCode;
        this.message = msge;
    }
}
