package com.nucleusteq.assessmentPlatform.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserEmailDomainExceptionTest {

    @Test
    public void testExceptionMessage() {
        String errorMessage = "Domain Name should be nucleusteq.com";
        UserEmailDomainException exception = new UserEmailDomainException(errorMessage);

        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

}
