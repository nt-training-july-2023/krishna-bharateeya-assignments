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
     * @return errorMap
     * @param exp methodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Map<String, String>>
    handleMethodArgumentNotValidException(
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
     * Handles NoSuchElementException by returning a response with a "Not Found"
     * status.
     * @param exception The NoSuchElementException to handle.
     * @return A ResponseEntity with a "Not Found" status and an error message.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<String> handleNoSuchElement(
            final NoSuchElementException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    /**
     * Handles AlreadyExistsException by returning a response with a "Conflict"
     * status.
     * @param exception The AlreadyExistsException to handle.
     * @return A ResponseEntity with a "Conflict" status and an error message.
     */
    @ExceptionHandler(AlreadyExistsException.class)
    public final ResponseEntity<String> handleAlreadyExistException(
            final AlreadyExistsException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.CONFLICT);
    }

    /**
     * Handles DuplicateEmailException by returning a response with a "Conflict"
     * status.
     * @param exception The DuplicateEmailException to handle.
     * @return A ResponseEntity with a "Conflict" status and an error message.
     */
    @ExceptionHandler(DuplicateEmailException.class)
    public final ResponseEntity<String> handleDuplicateEmail(
            final DuplicateEmailException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.CONFLICT);
    }

    /**
     * Handles LoginFailedException by returning a response with an
     * "Unauthorized" status.
     * @param exception The LoginFailedException to handle.
     * @return A ResponseEntity with an "Unauthorized" status and an error
     *         message.
     */
    @ExceptionHandler(LoginFailedException.class)
    public final ResponseEntity<String> handleWrongCredentialException(
            final LoginFailedException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles ResourceNotFoundException by returning a response with a "Not
     * Found" status.
     * @param exception The ResourceNotFoundException to handle.
     * @return A ResponseEntity with a "Not Found" status and an error message.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<String> handleResourceNotFound(
            final ResourceNotFoundException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.NOT_FOUND);
    }

    /**
     * Handles DuplicateMobileNumberException by returning a response with a
     * "Conflict" status.
     * @param exception The DuplicateMobileNumberException to handle.
     * @return A ResponseEntity with a "Conflict" status and an error message.
     */
    @ExceptionHandler(DuplicateMobileNumberException.class)
    public final ResponseEntity<String> handleDuplicateMobileNumber(
            final DuplicateMobileNumberException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.CONFLICT);
    }

    /**
     * Handles UserEmailDomainException by returning a response with a "Bad
     * Request" status.
     * @param exception The UserEmailDomainException to handle.
     * @return A ResponseEntity with a "Bad Request" status and an error
     *         message.
     */
    @ExceptionHandler(UserEmailDomainException.class)
    public final ResponseEntity<String> handleEmailDomain(
            final UserEmailDomainException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles UserNotFoundException by returning a response with a "Not Found"
     * status.
     * @param exception The UserNotFoundException to handle.
     * @return A ResponseEntity with a "Not Found" status and an error message.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<String> handleUserNotFound(
            final UserNotFoundException exception) {
        return new ResponseEntity<String>(exception.getMessage(),
                HttpStatus.NOT_FOUND);
    }
}
