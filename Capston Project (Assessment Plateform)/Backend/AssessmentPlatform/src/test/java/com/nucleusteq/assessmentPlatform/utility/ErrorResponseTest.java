package com.nucleusteq.assessmentPlatform.utility;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ErrorResponseTest {

    @Test
    public void testConstructorWithStatusCodeAndMessage() {

        int statusCode = 404;
        String message = "Not Found";
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
        assertNotNull(errorResponse.getErrors());
        assertEquals(errors.size(), errorResponse.getErrors().size());
        assertEquals(errors.get("field1"), errorResponse.getErrors().get("field1"));
        assertEquals(errors.get("field2"), errorResponse.getErrors().get("field2"));
    }

    @Test
    public void testGetErrorsWithNonNullErrors() {

        Map<String, String> errors = new HashMap<>();
        errors.put("field1", "Error 1");
        errors.put("field2", "Error 2");
        ErrorResponse errorResponse = new ErrorResponse(400, errors);

        Map<String, String> result = errorResponse.getErrors();

        assertNotNull(result);
        assertEquals(errors.size(), result.size());
        assertEquals(errors.get("field1"), result.get("field1"));
        assertEquals(errors.get("field2"), result.get("field2"));
    }

    @Test
    public void testGetErrorsWithNullErrors() {

        ErrorResponse errorResponse = new ErrorResponse(404, "Not Found");
        Map<String, String> result = errorResponse.getErrors();
        assertNull(result);
    }

    @Test
    public void testSetErrors() {

        ErrorResponse errorResponse = new ErrorResponse(400, "Bad Request");
        Map<String, String> errors = new HashMap<>();
        errors.put("field1", "Error 1");
        errors.put("field2", "Error 2");
        errorResponse.setErrors(errors);

        assertNotNull(errorResponse.getErrors());
        assertEquals(errors.size(), errorResponse.getErrors().size());
        assertEquals(errors.get("field1"), errorResponse.getErrors().get("field1"));
        assertEquals(errors.get("field2"), errorResponse.getErrors().get("field2"));
    }  
    
    @Test
    public void testConstructorWithStatusCodeMessageAndErrors() {
        int statusCode = 500;
        String message = "Internal Server Error";
        Map<String, String> errors = new HashMap<>();
        errors.put("field1", "Error 1");
        errors.put("field2", "Error 2");

        ErrorResponse errorResponse = new ErrorResponse(statusCode, message, errors);
        assertEquals(statusCode, errorResponse.getStatusCode());
        assertEquals(message, errorResponse.getMessage());
        assertNotNull(errorResponse.getErrors());
        assertEquals(errors.size(), errorResponse.getErrors().size());
        assertEquals(errors.get("field1"), errorResponse.getErrors().get("field1"));
        assertEquals(errors.get("field2"), errorResponse.getErrors().get("field2"));
    }
}
