package com.nucleusteq.assessmentPlatform.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.entity.Registration;
import com.nucleusteq.assessmentPlatform.exception.DuplicateEmailException;
import com.nucleusteq.assessmentPlatform.repository.RegistrationRepository;

@Service
public class RegistrationService {

	@Autowired
	RegistrationRepository registrationRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public String addUser(Registration registration) {
		if (registration != null && registration.getEmail() != null && registration.getPassword() != null) {

			 Optional<Registration> existingUserByEmail = registrationRepository.findByEmail(registration.getEmail());
			 if (existingUserByEmail.isPresent()) {
				 throw new DuplicateEmailException("Email address already exists");
			 }
			
			Registration newregistration = new Registration(
					0, 
					registration.getUserName(),
					"user",
					registration.getEmail(),
					this.passwordEncoder.encode(registration.getPassword())
					);

			try {
				registrationRepository.save(newregistration);
			} catch (Exception e) {
				throw e;
			}
			return registration.getUserName() + " Registered Successfully";

		} else {
			return ("registration object cannot be null");
		}
	}

	public List<Registration> getAllRegistrations() {
		return registrationRepository.findAll();
	}

	public Map<String, String> loginUser(Registration inputRegistration) {
		Map<String, String> response = new HashMap<>();

		Registration foundregistration = registrationRepository.getByEmail(inputRegistration.getEmail());

		if (foundregistration != null) {
			String password = inputRegistration.getPassword();
			String encodePassword = foundregistration.getPassword();

			boolean isRightPassword = passwordEncoder.matches(password, encodePassword);

			if (isRightPassword) {
				Optional<Registration> optionalregistration = registrationRepository
						.findByEmailAndPassword(inputRegistration.getEmail(), encodePassword);

				if (optionalregistration.isPresent()) {
					response.put("message", "Login Successfully");
					response.put("status", "true");
					response.put("role", foundregistration.getUserRole());
				} else {
					response.put("status", "false");
					response.put("message", "Login Failed");
				}
			} else {
				response.put("status", "false");
				response.put("message", "Password Not Match");
			}
		} else {
			response.put("status", "false");
			response.put("message", "User does not Exists");
		}

		return response;
	}


}
