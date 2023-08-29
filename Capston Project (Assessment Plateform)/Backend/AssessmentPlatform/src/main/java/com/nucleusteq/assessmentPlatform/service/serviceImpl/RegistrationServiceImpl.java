package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public Registration dtoToRegistration(
            final RegistrationDto registrationDto) {
        Registration registration = new Registration();

        registration.setFirstName(registrationDto.getFirstName());
        registration.setLastName(registrationDto.getLastName());
        registration.setMobileNumber(registrationDto.getMobileNumber());
        registration.setUserRole("user");
        registration.setEmail(registrationDto.getEmail());
        registration.setPassword(registrationDto.getPassword());

//	   registration.setType(userDto.getType());
        return registration;
    }

    /**
     * Entity to dto conversion.
     * 
     * @param user entity
     * @return converting entity to DTO
     */
    public RegistrationDto RegistrationToDto(final Registration registration) {
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

    public String addUser(RegistrationDto registrationDto)
            throws UserEmailDomainException, DuplicateMobileNumberException,
            DuplicateEmailException {
        if (registrationDto != null && registrationDto.getEmail() != null
                && registrationDto.getPassword() != null) {
            final String EMAIL_DOMAIN = "nucleusteq.com";

            if (!registrationDto.getEmail().endsWith("@" + EMAIL_DOMAIN)) {
                throw new UserEmailDomainException(
                        "Email domain should be " + EMAIL_DOMAIN);
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

    public List<RegistrationDto> getAllRegistrations() {
        List<Registration> registrations = registrationRepository.findAll();
        return registrations.stream().map(this::RegistrationToDto)
                .collect(Collectors.toList());
    }

    public Map<String, String> loginUser(RegistrationDto inputRegistrationDto)
            throws LoginFailedException, UserNotFoundException {
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

    @Override
    public RegistrationDto getUserById(int userId)
            throws UserNotFoundException {
        Registration registration = this.registrationRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found with ID: " + userId));
        return RegistrationToDto(registration);
    }

}
