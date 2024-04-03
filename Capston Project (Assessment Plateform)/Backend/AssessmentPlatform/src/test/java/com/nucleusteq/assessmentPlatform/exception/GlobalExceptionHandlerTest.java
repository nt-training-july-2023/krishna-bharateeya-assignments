package com.nucleusteq.assessmentPlatform.exception;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.nucleusteq.assessmentPlatform.utility.ErrorResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    public void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
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
    public void testhandleDuplicateOptionException() {
        DuplicateOptionException exception = new DuplicateOptionException("All options must be unique.");
        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleDuplicateOptionException(exception);
        String errorMessage = responseEntity.getBody().getMessage();

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("All options must be unique.", errorMessage);
    }

    @Test
    public void handleInvilidRequest() {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("please enter the request body.");
        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleInvilidRequest(exception);
        String errorMessage = responseEntity.getBody().getMessage();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("please enter the request body.", errorMessage);
    }
   

    @Test
    public void testHandleMethodArgumentNotValidException() {
        
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "fieldName", "Field error message");
        when(bindingResult.getAllErrors()).thenReturn(java.util.Collections.singletonList(fieldError));

        when(exception.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<ErrorResponse> response = exceptionHandler.handleMethodArgumentNotValidException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Field error message", response.getBody().getErrors().get("fieldName"));
    }

    @Test
    public void testHandleNoSuchElement() {
        NoSuchElementException exception = new NoSuchElementException("Element not found");
        
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleNoSuchElement(exception);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Element not found", response.getBody().getMessage());
    }


    @Test
    public void testHandleDuplicateResourceException() {

        DuplicateResourceException exception = new DuplicateResourceException("Duplicate email");
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleDuplicateResourceException(exception);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Duplicate email", response.getBody().getMessage());
    }

    @Test
    public void testHandleWrongCredentialException() {
        LoginFailedException exception = new LoginFailedException("Login failed");
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleWrongCredentialException(exception);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Login failed", response.getBody().getMessage());
    }    

    
}
