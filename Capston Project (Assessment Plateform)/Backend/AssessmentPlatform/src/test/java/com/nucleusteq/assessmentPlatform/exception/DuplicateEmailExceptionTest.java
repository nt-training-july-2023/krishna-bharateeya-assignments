package com.nucleusteq.assessmentPlatform.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

class DuplicateEmailExceptionTest {

    @Test
    public void testExceptionMessage() {
        String errorMessage = "Email address is already in use.";
        DuplicateEmailException exception = new DuplicateEmailException(errorMessage);

        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testExceptionInheritance() {
        String errorMessage = "Email address is already in use.";
        DuplicateEmailException exception = new DuplicateEmailException(errorMessage);

        Assertions.assertTrue(exception instanceof RuntimeException);
    }

    @Test
    public void testResponseStatusAnnotation() {
        ResponseStatus responseStatus = DuplicateEmailException.class.getAnnotation(ResponseStatus.class);

        Assertions.assertNotNull(responseStatus);
        Assertions.assertEquals(HttpStatus.CONFLICT, responseStatus.value());
    }

}
