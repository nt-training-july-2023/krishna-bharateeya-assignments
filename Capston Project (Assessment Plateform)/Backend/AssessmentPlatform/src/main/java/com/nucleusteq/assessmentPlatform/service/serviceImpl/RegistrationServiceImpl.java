package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.dto.LoginRequestDto;
import com.nucleusteq.assessmentPlatform.dto.RegistrationDto;
import com.nucleusteq.assessmentPlatform.entity.Registration;
import com.nucleusteq.assessmentPlatform.exception.DuplicateResourceException;
import com.nucleusteq.assessmentPlatform.exception.LoginFailedException;
import com.nucleusteq.assessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.assessmentPlatform.repository.RegistrationRepository;
import com.nucleusteq.assessmentPlatform.service.RegistrationService;
import com.nucleusteq.assessmentPlatform.utility.Message;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the RegistrationService interface for managing user
 * registrations and logins.
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    /**
     * This is Registration Repository object that is for calling. the
     * repository methods.
     */
    @Autowired
    private RegistrationRepository registrationRepository;

    /**
     * This is password encoder object it is for encryption of password field.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RegistrationServiceImpl.class);

    /**
     * Converts a RegistrationDto to a Registration entity.
     * @param registrationDto The RegistrationDto to convert.
     * @return The converted Registration entity.
     */
    public final Registration dtoToRegistration(
            final RegistrationDto registrationDto) {
        Registration registration = new Registration();

        registration.setFirstName(registrationDto.getFirstName());
        registration.setLastName(registrationDto.getLastName());
        registration.setMobileNumber(registrationDto.getMobileNumber());
        registration.setUserRole("user");
        registration.setEmail(registrationDto.getEmail());
        registration.setPassword(registrationDto.getPassword());
        return registration;
    }

    /**
     * Converts a Registration entity to a RegistrationDto.
     * @param registration The Registration entity to convert.
     * @return The converted RegistrationDto.
     */
    public final RegistrationDto registrationToDto(
            final Registration registration) {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUserId(registration.getUserId());
        registrationDto.setFirstName(registration.getFirstName());
        registrationDto.setLastName(registration.getLastName());
        registrationDto.setMobileNumber(registration.getMobileNumber());
        registrationDto.setUserRole(registration.getUserRole());
        registrationDto.setEmail(registration.getEmail());
        registrationDto.setPassword(null);
        return registrationDto;
    }

    /**
     * Adds a new user registration.
     *
     * @param registrationDto The DTO containing user registration information.
     * @return A message indicating the result of the operation.
     * @throws UserEmailDomainException       If the user's email domain is
     *                                        invalid.
     * @throws DuplicateMobileNumberException If the mobile number is already
     *                                        registered.
     * @throws DuplicateEmailException        If the email address is already
     *                                        registered.
     */
    public final SuccessResponse addUser(
            final RegistrationDto registrationDto) {

        registrationRepository
                .findByMobileNumber(registrationDto.getMobileNumber())
                .ifPresent(existingUser -> {
                    LOGGER.error(Message.DUPLICATE_MOBILE_NUMBER);
                    throw new DuplicateResourceException(
                            Message.DUPLICATE_MOBILE_NUMBER);
                });

        registrationRepository.findByEmail(registrationDto.getEmail())
                .ifPresent(existingUser -> {
                    LOGGER.error(Message.DUPLICATE_EMAIL);
                    throw new DuplicateResourceException(
                            Message.DUPLICATE_EMAIL);
                });

        Registration newRegistration = dtoToRegistration(registrationDto);
        newRegistration.setPassword(
                passwordEncoder.encode(registrationDto.getPassword()));
        registrationRepository.save(newRegistration);
        return new SuccessResponse(HttpStatus.CREATED.value(),
           registrationDto.getFirstName() + Message.REGISTERED_SUCCESSFULLY);
    }

    /**
     * Retrieves a list of all user registrations.
     * @return A list of RegistrationDto objects representing user
     *         registrations.
     */
    public final List<RegistrationDto> getAllRegistrations() {
        List<Registration> registrations = registrationRepository.findAll();
        return registrations.stream().map(this::registrationToDto)
                .collect(Collectors.toList());
    }

    /**
     * Logs in a user.
     * @param inputRegistrationDto The DTO containing user login credentials.
     * @return A map containing login response information.
     * @throws LoginFailedException  If the login attempt fails.
     * @throws UserNotFoundException If the user is not found.
     */
    public final Map<String, String> loginUser(
            final LoginRequestDto inputRegistrationDto)
            throws LoginFailedException, ResourceNotFoundException {
        Map<String, String> response = new HashMap<>();

        Registration foundRegistration = registrationRepository
                .getByEmail(inputRegistrationDto.getEmail());

        if (foundRegistration == null) {
            LOGGER.error(Message.USER_NOT_FOUND);
            throw new ResourceNotFoundException(Message.USER_NOT_FOUND_BY_EMAIL
                    +inputRegistrationDto.getEmail());
        }

        String password = inputRegistrationDto.getPassword();
        String encodedPassword = foundRegistration.getPassword();

        boolean isRightPassword = passwordEncoder.matches(password,
                encodedPassword);

        if (isRightPassword) {
            response.put("message", Message.LOGIN_SUCCESSFULLY);
            response.put("email", foundRegistration.getEmail());
            response.put("role", foundRegistration.getUserRole());
        } else {
            LOGGER.error(Message.LOGIN_FAILED);
            throw new LoginFailedException(
                    Message.LOGIN_FAILED);
        }
        return response;
    }

    /**
     * Retrieves user information by user ID.
     * @param userId The ID of the user to retrieve.
     * @return The RegistrationDto representing the user.
     * @throws UserNotFoundException
     */
    @Override
    public final RegistrationDto getUserById(final int userId)
            throws ResourceNotFoundException {
        Registration registration = this.registrationRepository.findById(userId)
                .orElse(null);

        if (registration == null) {
            LOGGER.error(Message.USER_NOT_FOUND + userId);
            throw new ResourceNotFoundException(
                    Message.USER_NOT_FOUND + userId);
        }
        return registrationToDto(registration);
    }

    /**
     * Retrieves user information by user ID.
     * @param email The email of the user to retrieve.
     * @return The RegistrationDto representing the user.
     * @throws UserNotFoundException If the user is not found.
     */
    @Override
    public final RegistrationDto getUserByEmail(final String email)
            throws ResourceNotFoundException {
        Optional<Registration> user = registrationRepository.findByEmail(email);

        return user.map(foundRegistration -> {
            RegistrationDto registrationDto = registrationToDto(
                    foundRegistration);
            return registrationDto;
        }).orElseThrow(() -> {
            LOGGER.error(Message.USER_NOT_FOUND_BY_EMAIL + email);
            return new ResourceNotFoundException(
                    Message.USER_NOT_FOUND_BY_EMAIL + email);
        });
    }

}
