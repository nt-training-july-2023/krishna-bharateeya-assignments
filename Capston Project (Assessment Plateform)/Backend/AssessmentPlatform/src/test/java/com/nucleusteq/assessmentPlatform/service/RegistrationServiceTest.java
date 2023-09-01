package com.nucleusteq.assessmentPlatform.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nucleusteq.assessmentPlatform.dto.RegistrationDto;
import com.nucleusteq.assessmentPlatform.entity.Registration;
import com.nucleusteq.assessmentPlatform.exception.DuplicateEmailException;
import com.nucleusteq.assessmentPlatform.exception.DuplicateMobileNumberException;
import com.nucleusteq.assessmentPlatform.exception.LoginFailedException;
import com.nucleusteq.assessmentPlatform.exception.UserEmailDomainException;
import com.nucleusteq.assessmentPlatform.exception.UserNotFoundException;
import com.nucleusteq.assessmentPlatform.repository.RegistrationRepository;
import com.nucleusteq.assessmentPlatform.service.serviceImpl.RegistrationServiceImpl;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private RegistrationRepository registrationRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private RegistrationServiceImpl registrationService;
    
    @SuppressWarnings("deprecation")
    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testAddUser_Success() throws DuplicateMobileNumberException, DuplicateEmailException, UserEmailDomainException {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUserId(101);
        registrationDto.setUserRole("user");
        registrationDto.setEmail("test@example.com");
        registrationDto.setPassword("password");
        registrationDto.setFirstName("Test");
        registrationDto.setLastName("User");
        registrationDto.setMobileNumber("1234567890");

        // Mock behavior for UserRepository
        when(registrationRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(registrationRepository.findByMobileNumber(anyString())).thenReturn(Optional.empty());

        // Mock behavior for PasswordEncoder
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        String resultMessage = registrationService.addUser(registrationDto);
        assertEquals("Test Registered Successfully", resultMessage);
    }
    

    @Test
    public void testAddUser_DuplicateEmail() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("test@example.com");

        // Mock behavior for UserRepository
        when(registrationRepository.findByEmail(anyString())).thenReturn(Optional.of(new Registration()));

        assertThrows(DuplicateEmailException.class, () -> registrationService.addUser(registrationDto));
    }

    @Test
    public void testAddUser_DuplicateMobileNumber() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setMobileNumber("1234567890");

        // Mock behavior for UserRepository
        when(registrationRepository.findByMobileNumber(anyString())).thenReturn(Optional.of(new Registration()));

        assertThrows(DuplicateMobileNumberException.class, () -> registrationService.addUser(registrationDto));
    }

    @Test
    public void testAddUser_InvalidEmailDomain() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("test@gmail.com");

        assertThrows(UserEmailDomainException.class, () -> registrationService.addUser(registrationDto));
    }

    @Test
    public void testLoginUser_Success() throws LoginFailedException, UserNotFoundException {
        RegistrationDto inputDto = new RegistrationDto();
        inputDto.setEmail("test@example.com");
        inputDto.setPassword("password");

        Registration registration = new Registration();
        registration.setEmail("test@example.com");
        registration.setPassword("encodedPassword");

        // Mock behavior for UserRepository
        when(registrationRepository.getByEmail(anyString())).thenReturn(registration);

        // Mock behavior for PasswordEncoder
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        Map<String, String> response = registrationService.loginUser(inputDto);

        assertEquals("Login Successfully", response.get("message"));
        assertEquals("true", response.get("status"));
        assertEquals("user", response.get("role"));
    }

    @Test
    public void testLoginUser_WrongPassword() {
        RegistrationDto inputDto = new RegistrationDto();
        inputDto.setEmail("test@example.com");
        inputDto.setPassword("wrongPassword");

        Registration registration = new Registration();
        registration.setEmail("test@example.com");
        registration.setPassword("encodedPassword");

        // Mock behavior for UserRepository
        when(registrationRepository.getByEmail(anyString())).thenReturn(registration);

        // Mock behavior for PasswordEncoder
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertThrows(LoginFailedException.class, () -> registrationService.loginUser(inputDto));
    }

    @Test
    public void testLoginUser_UserNotFound() {
        RegistrationDto inputDto = new RegistrationDto();
        inputDto.setEmail("test@example.com");
        inputDto.setPassword("password");

        // Mock behavior for UserRepository
        when(registrationRepository.getByEmail(anyString())).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> registrationService.loginUser(inputDto));
    }

    @Test
    public void testGetUserById_Success() throws UserNotFoundException {
        Registration registration = new Registration();
        registration.setUserId(1);
        registration.setEmail("test@example.com");

        // Mock behavior for UserRepository
        when(registrationRepository.findById(anyInt())).thenReturn(Optional.of(registration));

        RegistrationDto resultDto = registrationService.getUserById(1);

        assertEquals(1, resultDto.getUserId());
        assertEquals("test@example.com", resultDto.getEmail());
    }

    @Test
    public void testGetUserById_UserNotFound() {
        // Mock behavior for UserRepository
        when(registrationRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> registrationService.getUserById(1));
    }

    @Test
    public void testGetAllRegistrations() {
        Registration registration1 = new Registration();
        registration1.setUserId(1);
        registration1.setEmail("test1@example.com");

        Registration registration2 = new Registration();
        registration2.setUserId(2);
        registration2.setEmail("test2@example.com");

        List<Registration> registrationList = Arrays.asList(registration1, registration2);

        // Mock behavior for UserRepository
        when(registrationRepository.findAll()).thenReturn(registrationList);

        List<RegistrationDto> resultDtoList = registrationService.getAllRegistrations();

        assertEquals(2, resultDtoList.size());
        assertEquals(1, resultDtoList.get(0).getUserId());
        assertEquals("test1@example.com", resultDtoList.get(0).getEmail());
        assertEquals(2, resultDtoList.get(1).getUserId());
        assertEquals("test2@example.com", resultDtoList.get(1).getEmail());
    }

}
