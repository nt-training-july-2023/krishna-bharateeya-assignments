package com.nucleusteq.assessmentPlatform.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoginFailedExceptionTest {

    @Test
    public void testExceptionMessage() {
        String errorMessage = "Invilid Crediantial.";
        LoginFailedException exception = new LoginFailedException(errorMessage);

        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

}
