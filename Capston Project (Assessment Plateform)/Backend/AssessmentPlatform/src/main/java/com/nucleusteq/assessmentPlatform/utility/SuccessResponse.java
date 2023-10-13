package com.nucleusteq.assessmentPlatform.utility;

import java.util.Objects;

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

    /**
     * Indicates whether some other object is "equal to" this SuccessResponse.
     * both response considered equal if they have the same HTTP
     * status code and the same message.
     *
     * @param o The reference object with which to compare.
     * @return {@code true} if this Response is equal to the given object;
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SuccessResponse response = (SuccessResponse) o;
        return statusCode == response.statusCode
                && Objects.equals(message, response.message);
    }
    /**
     * Returns a hash code value for this Response based on its HTTP status
     * code and message.
     *
     * @return A hash code value for this SuccessResponse.
     */
    @Override
    public int hashCode() {
        return Objects.hash(statusCode, message);
    }
}
