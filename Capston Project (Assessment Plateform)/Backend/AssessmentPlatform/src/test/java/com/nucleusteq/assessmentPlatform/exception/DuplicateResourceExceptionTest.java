package com.nucleusteq.assessmentPlatform.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DuplicateResourceExceptionTest {

    @Test
    public void testExceptionMessage() {
        String errorMessage = "Data Already Exist.";
        DuplicateResourceException exception = new DuplicateResourceException(errorMessage);
        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

}
