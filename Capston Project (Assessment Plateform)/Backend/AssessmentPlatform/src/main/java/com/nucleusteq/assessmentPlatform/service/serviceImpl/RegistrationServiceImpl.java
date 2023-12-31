package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.dto.RegistrationDto;
import com.nucleusteq.assessmentPlatform.entity.Registration;
import com.nucleusteq.assessmentPlatform.exception.DuplicateEmailException;
import com.nucleusteq.assessmentPlatform.exception.DuplicateMobileNumberException;
import com.nucleusteq.assessmentPlatform.exception.LoginFailedException;
import com.nucleusteq.assessmentPlatform.exception.UserEmailDomainException;
import com.nucleusteq.assessmentPlatform.exception.UserNotFoundException;
import com.nucleusteq.assessmentPlatform.repository.RegistrationRepository;
import com.nucleusteq.assessmentPlatform.service.RegistrationService;

/**
 * Implementation of the RegistrationService interface for managing user
 * registrations and logins.
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    /**
     * This is Registration Repository object that is for calling.
     * the repository methods.
     */
    @Autowired
    private RegistrationRepository registrationRepository;

    /**
     * This is password encoder object it is for encryption of password field.
     *
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Converts a RegistrationDto to a Registration entity.
     *
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
     *
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
     * @throws UserEmailDomainException If the user's email domain is
     *                                        invalid.
     * @throws DuplicateMobileNumberException If the mobile number is already
     *                                        registered.
     * @throws DuplicateEmailException If the email address is already
     *                                        registered.
     */
    public final String addUser(final RegistrationDto registrationDto)
            throws UserEmailDomainException, DuplicateMobileNumberException,
            DuplicateEmailException {
        if (registrationDto != null && registrationDto.getEmail() != null
                && registrationDto.getPassword() != null) {
            final String emailDomain = "@nucleusteq.com";

            if (!registrationDto.getEmail().endsWith(emailDomain)) {
                throw new UserEmailDomainException(
                        "Email domain should be " + emailDomain);
            }

            Optional<Registration> existingUserByMobile = registrationRepository
                    .findByMobileNumber(registrationDto.getMobileNumber());
            if (existingUserByMobile.isPresent()) {
                throw new DuplicateMobileNumberException(
                        "Mobile number already exists");
            }

            Optional<Registration> existingUserByEmail = registrationRepository
                    .findByEmail(registrationDto.getEmail());
            if (existingUserByEmail.isPresent()) {
                throw new DuplicateEmailException(
                        "Email address already exists");
            }

            Registration newRegistration = dtoToRegistration(registrationDto);
            newRegistration.setPassword(
                    passwordEncoder.encode(registrationDto.getPassword()));
            registrationRepository.save(newRegistration);

            return registrationDto.getFirstName() + " Registered Successfully";
        } else {
            return ("registration object cannot be null");
        }
    }

    /**
     * Retrieves a list of all user registrations.
     *
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
     *
     * @param inputRegistrationDto The DTO containing user login credentials.
     * @return A map containing login response information.
     * @throws LoginFailedException  If the login attempt fails.
     * @throws UserNotFoundException If the user is not found.
     */
    public final Map<String, String> loginUser(final
            RegistrationDto inputRegistrationDto)throws
             LoginFailedException, UserNotFoundException {
        Map<String, String> response = new HashMap<>();

        Registration foundRegistration = registrationRepository
                .getByEmail(inputRegistrationDto.getEmail());

        if (foundRegistration != null) {
            String password = inputRegistrationDto.getPassword();
            String encodedPassword = foundRegistration.getPassword();

            boolean isRightPassword = passwordEncoder.matches(password,
                    encodedPassword);
            if (isRightPassword) {
            Optional<Registration> optionalRegistration = registrationRepository
                    .findByEmailAndPassword(inputRegistrationDto.getEmail(),
                            encodedPassword);

          if (optionalRegistration.isPresent()) {
                response.put("message", "Login Successfully");
                response.put("status", "true");
                response.put("role", foundRegistration.getUserRole());
            } else {
                throw new LoginFailedException(
                        "Login failed. Please check your credentials.");
            }
          } else {
            throw new LoginFailedException(
                    "Login failed. Please check your credentials.");
          }
        } else {
            throw new UserNotFoundException("User Does Not Exist");
        }

        return response;
    }

    /**
     * Retrieves user information by user ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The RegistrationDto representing the user.
     * @throws UserNotFoundException If the user is not found.
     */
    @Override
    public final RegistrationDto getUserById(final int userId)
            throws UserNotFoundException {
        Registration registration = this.registrationRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found with ID: " + userId));
        return registrationToDto(registration);
    }

}
