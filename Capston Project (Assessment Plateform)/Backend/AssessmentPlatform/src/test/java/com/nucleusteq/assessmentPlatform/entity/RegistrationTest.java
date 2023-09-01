package com.nucleusteq.assessmentPlatform.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistrationTest {

    Registration registration;
    @BeforeEach
    void init() {
        registration = new Registration();
    }
    
    @Test
    void testGettersAndSetters() {
        assertEquals(0,registration.getUserId());
        assertEquals(null, registration.getFirstName());
        assertEquals(null, registration.getLastName());
        assertEquals(null, registration.getMobileNumber());
        assertEquals(null, registration.getUserRole());
        assertEquals(null, registration.getEmail());
        assertEquals(null, registration.getPassword());
    }
    @Test
    void testParameterizedGettersAndSetters() {
        
        registration.setUserId(101);
        registration.setFirstName("Krishna");
        registration.setLastName("kumar");
        registration.setMobileNumber("3455645345");
        registration.setUserRole("user");
        registration.setEmail("kk@gmail.com");
        registration.setPassword("3456");
        assertEquals(101,registration.getUserId());
        assertEquals("Krishna", registration.getFirstName());
        assertEquals("kumar", registration.getLastName());
        assertEquals("3455645345", registration.getMobileNumber());
        assertEquals("user", registration.getUserRole());
        assertEquals("kk@gmail.com", registration.getEmail());
        assertEquals("3456", registration.getPassword());
    }
    
    @Test
    void allArgConstructor() {
        Registration allParaRegistration =new Registration(
                102,
                "anil",
                "kumar",
                "23456",
                "admin",
                "admin@gmail.com",
                "1234"
                );
        assertEquals(102,allParaRegistration.getUserId());
        assertEquals("anil", allParaRegistration.getFirstName());
        assertEquals("kumar", allParaRegistration.getLastName());
        assertEquals("23456", allParaRegistration.getMobileNumber());
        assertEquals("admin", allParaRegistration.getUserRole());
        assertEquals("admin@gmail.com", allParaRegistration.getEmail());
        assertEquals("1234", allParaRegistration.getPassword());
    }
}
