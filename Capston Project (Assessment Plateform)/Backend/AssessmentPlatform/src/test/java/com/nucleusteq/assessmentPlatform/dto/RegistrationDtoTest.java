package com.nucleusteq.assessmentPlatform.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RegistrationDtoTest {

    RegistrationDto registrationDto;
    @BeforeEach
    void init() {
        registrationDto = new RegistrationDto();
    }
    
    @Test
    void testGettersAndSetters() {
        assertEquals(0,registrationDto.getUserId());
        assertEquals(null, registrationDto.getFirstName());
        assertEquals(null, registrationDto.getLastName());
        assertEquals(null, registrationDto.getMobileNumber());
        assertEquals(null, registrationDto.getUserRole());
        assertEquals(null, registrationDto.getEmail());
        assertEquals(null, registrationDto.getPassword());
    }
    @Test
    void testParameterizedGettersAndSetters() {
        RegistrationDto parameterizedRegistrationDto=new RegistrationDto();
        parameterizedRegistrationDto.setUserId(101);
        parameterizedRegistrationDto.setFirstName("Krishna");
        parameterizedRegistrationDto.setLastName("kumar");
        parameterizedRegistrationDto.setMobileNumber("3455645345");
        parameterizedRegistrationDto.setUserRole("user");
        parameterizedRegistrationDto.setEmail("kk@gmail.com");
        parameterizedRegistrationDto.setPassword("3456");
        assertEquals(101,parameterizedRegistrationDto.getUserId());
        assertEquals("Krishna", parameterizedRegistrationDto.getFirstName());
        assertEquals("kumar", parameterizedRegistrationDto.getLastName());
        assertEquals("3455645345", parameterizedRegistrationDto.getMobileNumber());
        assertEquals("user", parameterizedRegistrationDto.getUserRole());
        assertEquals("kk@gmail.com", parameterizedRegistrationDto.getEmail());
        assertEquals("3456", parameterizedRegistrationDto.getPassword());
    }
    
    @Test
    void allArgConstructor() {
        RegistrationDto allParaRegistrationDto =new RegistrationDto(
                102,
                "anil",
                "kumar",
                "23456",
                "admin",
                "admin@gmail.com",
                "1234"
                );
        assertEquals(102,allParaRegistrationDto.getUserId());
        assertEquals("anil", allParaRegistrationDto.getFirstName());
        assertEquals("kumar", allParaRegistrationDto.getLastName());
        assertEquals("23456", allParaRegistrationDto.getMobileNumber());
        assertEquals("admin", allParaRegistrationDto.getUserRole());
        assertEquals("admin@gmail.com", allParaRegistrationDto.getEmail());
        assertEquals("1234", allParaRegistrationDto.getPassword());
    }
    

}
