package com.nucleusteq.assessmentPlatform.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.dto.RegistrationDto;
import com.nucleusteq.assessmentPlatform.exception.UserEmailDomainException;
import com.nucleusteq.assessmentPlatform.exception.UserNotFoundException;

@Service
public interface RegistrationService {

    public String addUser(RegistrationDto registrationDto)
            throws UserEmailDomainException;

    public RegistrationDto getUserById(int userId) throws UserNotFoundException;

    public List<RegistrationDto> getAllRegistrations();

    public Map<String, String> loginUser(RegistrationDto inputRegistrationDto)
            throws UserNotFoundException;

}
