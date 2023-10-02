package com.nucleusteq.assessmentPlatform.controller;
import com.nucleusteq.assessmentPlatform.dto.LoginRequestDto;
import com.nucleusteq.assessmentPlatform.dto.RegistrationDto;
import com.nucleusteq.assessmentPlatform.exception.UserNotFoundException;
import com.nucleusteq.assessmentPlatform.service.RegistrationService;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RegistrationControllerTest {

    @InjectMocks
    private RegistrationController registrationController;
    @Mock
    private RegistrationService registrationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        RegistrationDto userDto = new RegistrationDto();
        userDto.setFirstName("Krishna");
        userDto.setLastName("Kumar");
        userDto.setEmail("krishan@example.com");
        userDto.setPassword("password123");

        SuccessResponse successResponse = new SuccessResponse(HttpStatus.CREATED.value(),"Krishna Registered Successfully.");
        when(registrationService.addUser(userDto)).thenReturn(successResponse);

        ResponseEntity<SuccessResponse> responseEntity = registrationController.saveUser(userDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(successResponse, responseEntity.getBody());

        verify(registrationService).addUser(userDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(successResponse, responseEntity.getBody());
    }

    @Test
    public void testLoginUser() throws UserNotFoundException {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail("krishna@nucleusteq.com");
        loginRequestDto.setPassword("password123");

        when(registrationService.loginUser(loginRequestDto)).thenReturn(createAuthResponse());

        ResponseEntity<Map<String, String>> responseEntity = registrationController.loginUser(loginRequestDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Login successful", responseEntity.getBody().get("message"));
    }

    @Test
    public void testGetUserById() throws UserNotFoundException {
        int userId = 1;
        RegistrationDto expectedUser = createSampleUser(userId);

        when(registrationService.getUserById(userId)).thenReturn(expectedUser);

        ResponseEntity<RegistrationDto> responseEntity = registrationController.getUserById(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedUser, responseEntity.getBody());
    }

    @Test
    public void testGetAllUsers() {
        RegistrationDto sampleUser = createSampleUser(1);

        when(registrationService.getAllRegistrations()).thenReturn(List.of(sampleUser));

        ResponseEntity<List<RegistrationDto>> responseEntity = registrationController.getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(List.of(sampleUser), responseEntity.getBody());
    }

    private RegistrationDto createSampleUser(int userId) {
        RegistrationDto user = new RegistrationDto();
        user.setUserId(userId);
        user.setFirstName("krishna");
        user.setLastName("kumar");
        user.setEmail("krishna@nucleusteq.com");
        user.setPassword("password123");
        return user;
    }

    private Map<String, String> createAuthResponse() {
        Map<String, String> authResponse = new HashMap<>();
        authResponse.put("message", "Login successful");
        return authResponse;
    }
    
    @Test
    public void testGetUserByEmail() throws UserNotFoundException {
        String email = "krishna@nucleusteq.com";
        
        RegistrationDto user = new RegistrationDto();
        user.setUserId(1);
        user.setFirstName("krishan");
        user.setLastName("kumar");
        user.setEmail(email);
        user.setPassword("password123");
        when(registrationService.getUserByEmail(email)).thenReturn(user);

        ResponseEntity<RegistrationDto> responseEntity = registrationController.getUserByEmail(email);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }
}
