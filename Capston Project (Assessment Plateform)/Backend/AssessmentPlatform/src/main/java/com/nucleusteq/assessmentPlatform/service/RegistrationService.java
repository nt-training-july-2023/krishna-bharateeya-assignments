package com.nucleusteq.assessmentPlatform.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.dto.LoginRequestDto;
import com.nucleusteq.assessmentPlatform.dto.RegistrationDto;
import com.nucleusteq.assessmentPlatform.exception.UserEmailDomainException;
import com.nucleusteq.assessmentPlatform.exception.UserNotFoundException;

/**
 * Service interface for managing user registrations and login.
 */
@Service
public interface RegistrationService {

    /**
     * Adds a new user registration.
     *
     * @param registrationDto The DTO containing user registration information.
     * @return A message indicating the result of the operation.
     * @throws UserEmailDomainException If the user's email domain is invalid.
     */
    String addUser(RegistrationDto registrationDto)
            throws UserEmailDomainException;

    /**
     * Retrieves a user registration by its ID.
     *
     * @param userId The ID of the user registration to retrieve.
     * @return The retrieved RegistrationDto.
     * @throws UserNotFoundException If the user is not found.
     */
    RegistrationDto getUserById(int userId) throws UserNotFoundException;

    /**
     * Retrieves all user registrations.
     *
     * @return A list of all RegistrationDtos.
     */
    List<RegistrationDto> getAllRegistrations();

    /**
     * Attempts to log in a user.
     *
     * @param inputRegistrationDto The DTO containing login information.
     * @return A map containing authentication response.
     * @throws UserNotFoundException If the user is not found.
     */
    Map<String, String> loginUser(LoginRequestDto inputRegistrationDto)
            throws UserNotFoundException;

    /**
     * Retrieves a user registration by its ID.
     *
     * @param email The ID of the user registration to retrieve.
     * @return The retrieved RegistrationDto.
     * @throws UserNotFoundException If the user is not found.
     */
    RegistrationDto getUserByEmail(String email) throws UserNotFoundException;
}
