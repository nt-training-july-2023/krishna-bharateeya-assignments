package com.nucleusteq.assessmentPlatform.exception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DuplicateOptionExceptionTest {

    @Test
    public void testExceptionMessage() {
        String errorMessage = "Data Already exist.";
        DuplicateOptionException exception = new DuplicateOptionException(errorMessage);

        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

}
