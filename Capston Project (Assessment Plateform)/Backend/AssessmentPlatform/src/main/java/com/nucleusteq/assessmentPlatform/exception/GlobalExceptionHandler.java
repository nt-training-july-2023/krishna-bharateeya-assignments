package com.nucleusteq.assessmentPlatform.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception handles when arguments does not contain valid input.
     * 
     * @return errorMap
     * @param exception methodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException exp) {
        Map<String, String> response = new HashMap<>();
        exp.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            response.put(fieldName, message);
        });
        return new ResponseEntity<Map<String, String>>(response,
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handles when no element is present with id.
     * 
     * @return responseEntity
     * @param exception noSuchElementException
     */
    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<String> handleNoSuchElement(
            final NoSuchElementException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public final ResponseEntity<String> handleAlreadyExistException(
            final AlreadyExistsException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public final ResponseEntity<String> handleDuplicateEmail(
            final DuplicateEmailException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LoginFailedException.class)
    public final ResponseEntity<String> handleWrongCredentialException(
            final LoginFailedException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<String> handleResourceNotFound(
            final ResourceNotFoundException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(DuplicateMobileNumberException.class)
    public final ResponseEntity<String> handleDuplicateMobileNumber(
            final DuplicateMobileNumberException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(UserEmailDomainException.class)
    public final ResponseEntity<String> handleEmailDomain(
            final UserEmailDomainException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<String> handleUserNotFound(
            final UserNotFoundException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.NOT_FOUND);
    }
}
