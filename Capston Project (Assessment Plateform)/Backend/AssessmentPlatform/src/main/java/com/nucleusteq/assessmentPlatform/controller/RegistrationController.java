package com.nucleusteq.assessmentPlatform.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleusteq.assessmentPlatform.dto.LoginRequestDto;
import com.nucleusteq.assessmentPlatform.dto.RegistrationDto;
import com.nucleusteq.assessmentPlatform.exception.UserNotFoundException;
import com.nucleusteq.assessmentPlatform.service.RegistrationService;

import jakarta.validation.Valid;

/**
 * Controller class for handling user registration and login.
 */
@CrossOrigin
@RestController
@RequestMapping("/users")
public class RegistrationController {

    /**
     * This is registration service object it call the service method.
     *
     */
    @Autowired
    private RegistrationService registrationService;

    /**
     * this is logger object that is use to generate log.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RegistrationController.class);

    /**
     * Registers a new user.
     * @param user The RegistrationDto object containing user information.
     * @return A message indicating the result of the user registration.
     * @throws UserEmailDomainException If the user's email domain is invalid.
     */
    @PostMapping(path = "/save")
    public final ResponseEntity<String> saveUser(
            @Valid @RequestBody final RegistrationDto user) {
        LOGGER.info("Received a request to save a new user.");
        String response = registrationService.addUser(user);
        LOGGER.info("User Registration Successful.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Logs in a user.
     *
     * @param user The RegistrationDto object containing user login information.
     * @return A map containing the user's authentication response.
     * @throws UserNotFoundException If the user is not found.
     */
    @PostMapping(path = "/login")
    public final ResponseEntity<Map<String, String>> loginUser(
            @Valid @RequestBody final LoginRequestDto user)
            throws UserNotFoundException {
        LOGGER.info("Received a login request for user: {}", user.getEmail());

        Map<String, String> response = registrationService.loginUser(user);
        LOGGER.info("User {} logged in successfully.", user.getEmail());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves a user by their ID.
     * @param userId The ID of the user to retrieve.
     * @return The RegistrationDto object representing the retrieved user.
     * @throws UserNotFoundException If the user with the specified ID is not
     *                               found.
     */
    @GetMapping("/get/{id}")
    public final ResponseEntity<RegistrationDto> getUserById(
            @PathVariable("id") final int userId) throws UserNotFoundException {
        LOGGER.info("Received a request for user id: {}", userId);
        RegistrationDto registrationDto = this.registrationService
                .getUserById(userId);
        LOGGER.info("User {} found successfully.", userId);
        return new ResponseEntity<>(registrationDto, HttpStatus.OK);
    }

    /**
     * Retrieves all user.
     * @return The RegistrationDto object representing the retrieved user.
     * found.
     */
    @GetMapping("/get/all")
    public final ResponseEntity<List<RegistrationDto>> getAllUsers() {
        LOGGER.info("Received a request for Get all user.");
        return new ResponseEntity<>(registrationService.getAllRegistrations(),
                HttpStatus.OK);
    }

    /**
     * Retrieves a user by their ID.
     * @param email The ID of the user to retrieve.
     * @return The RegistrationDto object representing the retrieved user.
     * @throws UserNotFoundException If the user with the specified ID is not
     * found.
     */
    @GetMapping("/getUser/{email}")
    public final ResponseEntity<RegistrationDto> getUserByEmail(
            @PathVariable("email") final String email)
            throws UserNotFoundException {
        LOGGER.info("Received a request for Get all user.");
        RegistrationDto registrationDto = this.registrationService
                .getUserByEmail(email);
        return new ResponseEntity<>(registrationDto, HttpStatus.OK);
    }
}
