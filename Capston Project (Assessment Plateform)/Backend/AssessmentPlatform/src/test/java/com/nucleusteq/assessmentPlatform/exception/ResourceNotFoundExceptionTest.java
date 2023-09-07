package com.nucleusteq.assessmentPlatform.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ResourceNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        String errorMessage = "Data Not found.";
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);
        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

}
