package com.nucleusteq.assessmentPlatform.utility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuccessResponseTest {

    @Test
    public void testConstructorAndGetters() {
        int statusCode = 200;
        String message = "Success";

        SuccessResponse successResponse = new SuccessResponse(statusCode, message);

        assertEquals(statusCode, successResponse.getStatusCode());
        assertEquals(message, successResponse.getMessage());

        // Test setter methods
        int newStatusCode = 201;
        String newMessage = "Created";

        successResponse.setStatusCode(newStatusCode);
        successResponse.setMessage(newMessage);

        assertEquals(newStatusCode, successResponse.getStatusCode());
        assertEquals(newMessage, successResponse.getMessage());
    }
}
