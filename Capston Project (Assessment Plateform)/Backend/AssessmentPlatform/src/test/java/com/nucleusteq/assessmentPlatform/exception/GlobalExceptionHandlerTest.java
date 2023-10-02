package com.nucleusteq.assessmentPlatform.exception;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nucleusteq.assessmentPlatform.utility.ErrorResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    public void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }


    @Test
    public void testHandleAlreadyExistException() {
        AlreadyExistsException exception = new AlreadyExistsException("Resource already exists");
        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleAlreadyExistException(exception);
        String errorMessage = responseEntity.getBody().getMessage();
        
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("Resource already exists", errorMessage);
    }



    @Test
    public void testHandleResourceNotFoundException() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");
        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleResourceNotFound(exception);

        String errorMessage = responseEntity.getBody().getMessage();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Resource not found", errorMessage);
    }


    @Test
    public void testHandleUserNotFoundException() {
        UserNotFoundException exception = new UserNotFoundException("User not found");
        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleUserNotFound(exception);
        String errorMessage = responseEntity.getBody().getMessage();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("User not found", errorMessage);
    }

}
