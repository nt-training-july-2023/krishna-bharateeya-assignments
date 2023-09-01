package com.nucleusteq.assessmentPlatform.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        String errorMessage = "User does not exist.";
        UserNotFoundException exception = new UserNotFoundException(errorMessage);

        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

}
