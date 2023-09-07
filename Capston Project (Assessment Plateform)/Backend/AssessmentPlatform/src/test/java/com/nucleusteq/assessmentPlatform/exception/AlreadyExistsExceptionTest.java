package com.nucleusteq.assessmentPlatform.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AlreadyExistsExceptionTest {

    @Test
    public void testExceptionMessage() {
        String errorMessage = "Data Already exist.";
        AlreadyExistsException exception = new AlreadyExistsException(errorMessage);

        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

}
