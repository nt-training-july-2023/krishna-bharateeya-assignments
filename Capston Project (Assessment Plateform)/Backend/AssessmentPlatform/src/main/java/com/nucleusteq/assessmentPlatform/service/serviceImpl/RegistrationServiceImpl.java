package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	
	public String addUser(Registration registration) throws UserEmailDomainException, DuplicateMobileNumberException, DuplicateEmailException {
	    if (registration != null && registration.getEmail() != null && registration.getPassword() != null) {
	        final String EMAIL_DOMAIN = "nucleusteq.com";

	        if (!registration.getEmail().endsWith("@" + EMAIL_DOMAIN)) {
	            throw new UserEmailDomainException("Email domain should be " + EMAIL_DOMAIN);
	        }

	        Optional<Registration> existingUserByMobile = registrationRepository.findByMobileNumber(registration.getMobileNumber());
	        if (existingUserByMobile.isPresent()) {
	            throw new DuplicateMobileNumberException("Mobile number already exists");
	        }

	        Optional<Registration> existingUserByEmail = registrationRepository.findByEmail(registration.getEmail());
	        if (existingUserByEmail.isPresent()) {
	            throw new DuplicateEmailException("Email address already exists");
	        }

	        Registration newregistration = new Registration(
	                0,
	                registration.getFirstName(),
	                registration.getLastName(),
	                registration.getMobileNumber(),
	                "user",
	                registration.getEmail(),
	                this.passwordEncoder.encode(registration.getPassword())
	        );

	        registrationRepository.save(newregistration);

	        return registration.getFirstName() + " Registered Successfully";
	    } else {
	        return ("registration object cannot be null");
	    }
	}
	



	public List<Registration> getAllRegistrations() {
		return registrationRepository.findAll();
	}
	
	public Map<String, String> loginUser(Registration inputRegistration) throws LoginFailedException, UserNotFoundException {
	    Map<String, String> response = new HashMap<>();

	    Registration foundRegistration = registrationRepository.getByEmail(inputRegistration.getEmail());

	    if (foundRegistration != null) {
	        String password = inputRegistration.getPassword();
	        String encodedPassword = foundRegistration.getPassword();

	        boolean isRightPassword = passwordEncoder.matches(password, encodedPassword);
	        if (isRightPassword) {
	            Optional<Registration> optionalRegistration = registrationRepository
	                    .findByEmailAndPassword(inputRegistration.getEmail(), encodedPassword);

	            if (optionalRegistration.isPresent()) {
	                response.put("message", "Login Successfully");
	                response.put("status", "true");
	                response.put("role", foundRegistration.getUserRole());
	            } else {
	                throw new LoginFailedException("Login failed. Please check your credentials.");
	            }
	        } else {
	            throw new LoginFailedException("Login failed. Please check your credentials.");
	        }
	    } else {
	        throw new UserNotFoundException("User Does Not Exist");
	    }

	    return response;
	}


}
