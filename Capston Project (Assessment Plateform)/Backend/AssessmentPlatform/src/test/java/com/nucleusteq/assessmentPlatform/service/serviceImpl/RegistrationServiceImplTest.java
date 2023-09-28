package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nucleusteq.assessmentPlatform.dto.LoginRequestDto;
import com.nucleusteq.assessmentPlatform.dto.RegistrationDto;
import com.nucleusteq.assessmentPlatform.entity.Registration;
import com.nucleusteq.assessmentPlatform.exception.DuplicateEmailException;
import com.nucleusteq.assessmentPlatform.exception.DuplicateMobileNumberException;
import com.nucleusteq.assessmentPlatform.exception.LoginFailedException;
import com.nucleusteq.assessmentPlatform.exception.UserEmailDomainException;
import com.nucleusteq.assessmentPlatform.exception.UserNotFoundException;
import com.nucleusteq.assessmentPlatform.repository.RegistrationRepository;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {
    @Mock
    private RegistrationRepository registrationRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private RegistrationServiceImpl registrationService;
    
    @Mock
    ModelMapper modelMapper;
    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testAddUser_Success() throws DuplicateMobileNumberException, DuplicateEmailException, UserEmailDomainException {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUserRole("user");
        registrationDto.setEmail("test@nucleusteq.com");
        registrationDto.setPassword("password");
        registrationDto.setFirstName("Test");
        registrationDto.setLastName("User");
        registrationDto.setMobileNumber("1234567890");


        when(registrationRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(registrationRepository.findByMobileNumber(anyString())).thenReturn(Optional.empty());

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        String resultMessage = registrationService.addUser(registrationDto);
        assertEquals("Test Registered Successfully", resultMessage);
    }
    

    @Test
    public void testAddUser_DuplicateEmail() {
        RegistrationDto registration=new RegistrationDto();
        registration.setUserId(101);
        registration.setFirstName("Krishna");
        registration.setLastName("kumar");
        registration.setMobileNumber("3455645345");
        registration.setUserRole("user");
        registration.setEmail("vk@nucleusteq.com");
        registration.setPassword("3456");

        when(registrationRepository.findByEmail(anyString())).thenReturn(Optional.of(new Registration()));

        assertThrows(DuplicateEmailException.class, () -> registrationService.addUser(registration));
    }

    @Test
    public void testAddUser_DuplicateMobileNumber() {
        RegistrationDto registration=new RegistrationDto();
        registration.setUserId(101);
        registration.setFirstName("Krishna");
        registration.setLastName("kumar");
        registration.setMobileNumber("7777777777");
        registration.setUserRole("user");
        registration.setEmail("vkss@nucleusteq.com");
        registration.setPassword("3456");

        when(registrationRepository.findByMobileNumber(anyString())).thenReturn(Optional.of(new Registration()));

        assertThrows(DuplicateMobileNumberException.class, () -> registrationService.addUser(registration));
    }
    


    @Test
    public void testAddUser_InvalidEmailDomain() {
        RegistrationDto registration=new RegistrationDto();
        registration.setUserId(101);
        registration.setFirstName("Krishna");
        registration.setLastName("kumar");
        registration.setMobileNumber("7777777777");
        registration.setUserRole("user");
        registration.setEmail("vkss@gmail.com");
        registration.setPassword("3456");

        assertThrows(UserEmailDomainException.class, () -> registrationService.addUser(registration));
    }
    
    
//    @Test
//    public void testLoginUser_Success() throws LoginFailedException, UserNotFoundException, DuplicateMobileNumberException, DuplicateEmailException, UserEmailDomainException {
//        RegistrationDto registrationDto=new RegistrationDto();
//      
//        registrationDto.setEmail("vk@nucleusteq.com");
//        registrationDto.setPassword("123");
//
//        Registration registration = new Registration();
//        registration.setEmail(registrationDto.getEmail());
//        registration.setPassword(registrationDto.getPassword());
//
//        //        when(registrationRepository.getByEmail(registrationDto.getEmail())).thenReturn(registration);
//
//        when(registrationRepository.findByEmail(registrationDto.getEmail())).thenReturn(Optional.of(registration));
//        when(passwordEncoder.matches(any(), any())).thenReturn(true);
//        when(modelMapper.map(registrationDto, Registration.class)).thenReturn(registration);
//        when(modelMapper.map(registration, RegistrationDto.class)).thenReturn(registrationDto);
//        when(registrationRepository.save(any(Registration.class))).thenReturn(registration);
//           
//        Map<String, String> response = registrationService.loginUser(registrationDto);
//        assertNotNull(response);      
//        assertEquals("Login Successfully", response.get("message"));
//        assertEquals("true", response.get("status"));
//        assertEquals("user", response.get("role"));
//    }

    @Test
    public void testLoginUser_WrongPassword() {
        LoginRequestDto inputDto = new LoginRequestDto();
        inputDto.setEmail("test@example.com");
        inputDto.setPassword("wrongPassword");

        Registration registration = new Registration();
        registration.setEmail("test@example.com");
        registration.setPassword("encodedPassword");

        when(registrationRepository.getByEmail(anyString())).thenReturn(registration);

        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertThrows(LoginFailedException.class, () -> registrationService.loginUser(inputDto));
    }

    @Test
    public void testLoginUser_UserNotFound() {
        LoginRequestDto inputDto = new LoginRequestDto();
        inputDto.setEmail("test@example.com");
        inputDto.setPassword("password");
        when(registrationRepository.getByEmail(anyString())).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> registrationService.loginUser(inputDto));
    }

    @Test
    public void testGetUserById_Success() throws UserNotFoundException {
        Registration registration = new Registration();
        registration.setUserId(1);
        registration.setEmail("test@example.com");

        when(registrationRepository.findById(anyInt())).thenReturn(Optional.of(registration));

        RegistrationDto resultDto = registrationService.getUserById(1);

        assertEquals(1, resultDto.getUserId());
        assertEquals("test@example.com", resultDto.getEmail());
    }

    @Test
    public void testGetUserById_UserNotFound() {
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

        when(registrationRepository.findAll()).thenReturn(registrationList);

        List<RegistrationDto> resultDtoList = registrationService.getAllRegistrations();

        assertEquals(2, resultDtoList.size());
        assertEquals(1, resultDtoList.get(0).getUserId());
        assertEquals("test1@example.com", resultDtoList.get(0).getEmail());
        assertEquals(2, resultDtoList.get(1).getUserId());
        assertEquals("test2@example.com", resultDtoList.get(1).getEmail());
    }

}
