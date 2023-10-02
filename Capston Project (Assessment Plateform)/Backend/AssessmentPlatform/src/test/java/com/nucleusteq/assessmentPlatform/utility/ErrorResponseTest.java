package com.nucleusteq.assessmentPlatform.utility;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ErrorResponseTest {

    @Test
    public void testConstructorWithStatusCodeAndMessage() {
        int statusCode = 404;
        String message = "Resource not found";
        ErrorResponse errorResponse = new ErrorResponse(statusCode, message);

        assertEquals(statusCode, errorResponse.getStatusCode());
        assertEquals(message, errorResponse.getMessage());
        assertNull(errorResponse.getErrors());
    }

    @Test
    public void testConstructorWithStatusCodeAndErrors() {
        int statusCode = 400;
        Map<String, String> errors = new HashMap<>();
        errors.put("field1", "Error 1");
        errors.put("field2", "Error 2");

        ErrorResponse errorResponse = new ErrorResponse(statusCode, errors);

        assertEquals(statusCode, errorResponse.getStatusCode());
        assertNull(errorResponse.getMessage());
        assertEquals(errors, errorResponse.getErrors());
    }

    @Test
    public void testGettersAndSetters() {
        int statusCode = 500;
        String message = "Internal Server Error";
        Map<String, String> errors = new HashMap<>();
        errors.put("field1", "Error 1");

        ErrorResponse errorResponse = new ErrorResponse(200, "OK");

        errorResponse.setStatusCode(statusCode);
        errorResponse.setMessage(message);
        errorResponse.setErrors(errors);

        assertEquals(statusCode, errorResponse.getStatusCode());
        assertEquals(message, errorResponse.getMessage());
        assertEquals(errors, errorResponse.getErrors());
    }
}
