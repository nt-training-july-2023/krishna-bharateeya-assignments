package com.nucleusteq.assessmentPlatform.exception;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        ResponseEntity<String> responseEntity = exceptionHandler.handleAlreadyExistException(exception);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("Resource already exists", responseEntity.getBody());
    }


    @Test
    public void testHandleResourceNotFoundException() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");
        ResponseEntity<String> responseEntity = exceptionHandler.handleResourceNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Resource not found", responseEntity.getBody());
    }

    @Test
    public void testHandleUserEmailDomainException() {
        UserEmailDomainException exception = new UserEmailDomainException("Invalid email domain");
        ResponseEntity<String> responseEntity = exceptionHandler.handleEmailDomain(exception);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid email domain", responseEntity.getBody());
    }

    @Test
    public void testHandleUserNotFoundException() {
        UserNotFoundException exception = new UserNotFoundException("User not found");
        ResponseEntity<String> responseEntity = exceptionHandler.handleUserNotFound(exception);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("User not found", responseEntity.getBody());
    }
}
