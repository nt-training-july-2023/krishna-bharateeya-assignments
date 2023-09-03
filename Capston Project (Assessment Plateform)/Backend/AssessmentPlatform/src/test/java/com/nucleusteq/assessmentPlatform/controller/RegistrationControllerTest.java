package com.nucleusteq.assessmentPlatform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nucleusteq.assessmentPlatform.dto.RegistrationDto;
import com.nucleusteq.assessmentPlatform.entity.Registration;
import com.nucleusteq.assessmentPlatform.exception.UserNotFoundException;
import com.nucleusteq.assessmentPlatform.repository.RegistrationRepository;
import com.nucleusteq.assessmentPlatform.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RegistrationService registrationService;

    @InjectMocks
    private RegistrationController registrationController;

    @Autowired
    private ObjectMapper objectMapper1;


    @Mock
    private RegistrationRepository registrationRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    
    public RegistrationControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser_Success() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto();
       
        registrationDto.setFirstName("krishna");
        registrationDto.setLastName("kumar");
        registrationDto.setUserRole("user");
        registrationDto.setEmail("test@nucleusteq.com");
        registrationDto.setPassword("password");
        registrationDto.setMobileNumber("1234567890");

        when(registrationService.addUser(registrationDto))
                .thenReturn("krishna Registered Successfully");

        String response = registrationController.saveUser(registrationDto);
        assertEquals("krishna Registered Successfully", response);
    }
    
    @Test
    public void testLoginUser_Success() throws UserNotFoundException {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("test@example.com");
        registrationDto.setPassword("password");

        Registration foundRegistration = new Registration();
        foundRegistration.setEmail("test@example.com");
        foundRegistration.setPassword("$2a$10$.s2VFopitilOgjpjAxgZKuS9QxlWx5K0lyoh04R7t7GRuS2YBj6Ne"); 
        foundRegistration.setUserRole("user");

        when(registrationRepository.getByEmail("test@example.com")).thenReturn(foundRegistration);

        Map<String, String> successResponse = getSuccessResponse();
        when(registrationService.loginUser(registrationDto)).thenReturn(successResponse);

        Map<String, String> response = registrationController.loginUser(registrationDto);

   
        assertEquals("Login Successfully", response.get("message"));
        assertEquals("true", response.get("status"));
        assertEquals("user", response.get("role"));
    }

    


    private Map<String, String> getSuccessResponse() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login Successfully");
        response.put("status", "true");
        response.put("role", "user");
        return response;
    }

    @Test
    public void testGetUserById_Success() throws Exception {
        int userId = 1012;
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUserId(userId);
        registrationDto.setEmail("test@example.com");

        when(registrationService.getUserById(userId)).thenReturn(registrationDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/users/get/{id}", userId))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertEquals(objectMapper1.writeValueAsString(registrationDto), responseContent);
    }
    
    @Test
    public void testSaveUser_InvalidEmail() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("invalid-email");
        registrationDto.setPassword("password");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registrationDto)))
                .andExpect(status().isBadRequest()) 
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertTrue(responseContent.contains("Email format is invalid"));
    }

    @Test
    public void testLoginUser_UserNotFound() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("nonexistent@example.com");
        registrationDto.setPassword("password");

        when(registrationService.loginUser(registrationDto))
                .thenThrow(new UserNotFoundException("User not found"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registrationDto)))
                .andExpect(status().isNotFound()) 
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertTrue(responseContent.contains("User not found"));
    }

}
