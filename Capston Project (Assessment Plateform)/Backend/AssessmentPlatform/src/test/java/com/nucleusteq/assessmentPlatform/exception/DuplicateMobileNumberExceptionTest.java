package com.nucleusteq.assessmentPlatform.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DuplicateMobileNumberExceptionTest {

    @Test
    public void testExceptionMessage() {
        String errorMessage = "Mobile number is already in use.";
        DuplicateMobileNumberException exception = new DuplicateMobileNumberException(errorMessage);

        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

}
