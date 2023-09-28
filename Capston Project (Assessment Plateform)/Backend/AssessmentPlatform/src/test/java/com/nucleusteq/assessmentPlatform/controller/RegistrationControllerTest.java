package com.nucleusteq.assessmentPlatform.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.nucleusteq.assessmentPlatform.dto.LoginRequestDto;
import com.nucleusteq.assessmentPlatform.dto.RegistrationDto;
import com.nucleusteq.assessmentPlatform.service.RegistrationService;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService registrationService;

    private RegistrationDto sampleUserDto;

    @BeforeEach
    public void setUp() {
        sampleUserDto = new RegistrationDto();
        sampleUserDto.setUserId(1);
        sampleUserDto.setFirstName("John");
        sampleUserDto.setLastName("Doe");
        sampleUserDto.setEmail("john@example.com");
        sampleUserDto.setPassword("password123");
    }

    @Test
    public void testSaveUser() throws Exception {
        when(registrationService.addUser(any(RegistrationDto.class))).thenReturn("User registered successfully");

        mockMvc.perform(post("/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@example.com\",\"password\":\"password123\"}")
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));
    }

    @Test
    public void testLoginUser() throws Exception {
        Map<String, String> authResponse = new HashMap<>();
        authResponse.put("message", "Login successful");
        when(registrationService.loginUser(any(LoginRequestDto.class))).thenReturn(authResponse);
        
        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@example.com\",\"password\":\"password123\"}")
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Login successful"));
    }

    @Test
    public void testGetUserById() throws Exception {
        when(registrationService.getUserById(anyInt())).thenReturn(sampleUserDto);

        mockMvc.perform(get("/users/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(sampleUserDto.getUserId()))
                .andExpect(jsonPath("$.firstName").value(sampleUserDto.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(sampleUserDto.getLastName()))
                .andExpect(jsonPath("$.email").value(sampleUserDto.getEmail()))
                .andExpect(jsonPath("$.password").value(sampleUserDto.getPassword()));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<RegistrationDto> userDtoList = Collections.singletonList(sampleUserDto);
        when(registrationService.getAllRegistrations()).thenReturn(userDtoList);
        mockMvc.perform(get("/users/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(sampleUserDto.getUserId()))
                .andExpect(jsonPath("$[0].firstName").value(sampleUserDto.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(sampleUserDto.getLastName()))
                .andExpect(jsonPath("$[0].email").value(sampleUserDto.getEmail()))
                .andExpect(jsonPath("$[0].password").value(sampleUserDto.getPassword()));
    }
}
