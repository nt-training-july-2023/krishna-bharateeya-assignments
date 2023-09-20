package com.nucleusteq.assessmentPlatform.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleusteq.assessmentPlatform.dto.RegistrationDto;
import com.nucleusteq.assessmentPlatform.exception.UserEmailDomainException;
import com.nucleusteq.assessmentPlatform.exception.UserNotFoundException;
import com.nucleusteq.assessmentPlatform.service.RegistrationService;

import ch.qos.logback.classic.Logger;

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
     * this is logger object that is use to generate the object.
     */
    private static final Logger LOGGER = (Logger) LoggerFactory
            .getLogger(RegistrationController.class);

    /**
     * Registers a new user.
     *
     * @param user The RegistrationDto object containing user information.
     * @return A message indicating the result of the user registration.
     * @throws UserEmailDomainException If the user's email domain is invalid.
     */
    @PostMapping(path = "/save")
    public final String saveUser(@RequestBody final RegistrationDto user)
            throws UserEmailDomainException {
        LOGGER.info("Received a request to save a new user.");
        try {
            return registrationService.addUser(user);
        } catch (UserEmailDomainException e) {
            LOGGER.error("Error while saving user: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Logs in a user.
     *
     * @param user The RegistrationDto object containing user login information.
     * @return A map containing the user's authentication response.
     * @throws UserNotFoundException If the user is not found.
     */
    @PostMapping(path = "/login")
    public final Map<String, String> loginUser(
            @RequestBody final RegistrationDto user)
            throws UserNotFoundException {
        LOGGER.info("Received a login request for user: {}",
                user.getFirstName());
        try {
            Map<String, String> response = registrationService.loginUser(user);
            LOGGER.info("User {} logged in successfully.", user.getFirstName());

            return response;
        } catch (UserNotFoundException e) {
            LOGGER.error("User login failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The RegistrationDto object representing the retrieved user.
     * @throws UserNotFoundException If the user with the specified ID is not
     *                               found.
     */
    @GetMapping("/get/{id}")
    public final RegistrationDto getUserById(
            @PathVariable("id") final int userId) throws UserNotFoundException {
        LOGGER.info("Received a request for user id: {}", userId);
        try {
            RegistrationDto registrationDto = this.registrationService
                    .getUserById(userId);
            LOGGER.info("User {} found successfully.", userId);
            return registrationDto;
        } catch (UserNotFoundException e) {
            LOGGER.error("User does not exist: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves all user.
     * @return The RegistrationDto object representing the retrieved user.
     * found.
     */
    @GetMapping("/get/all")
    public final List<RegistrationDto> getAllUsers() {
        return registrationService.getAllRegistrations();
    }
    
    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The RegistrationDto object representing the retrieved user.
     * @throws UserNotFoundException If the user with the specified ID is not
     *                               found.
     */
    @GetMapping("/getUser/{email}")
    public final RegistrationDto getUserByEmail(
            @PathVariable("email") final String  email) throws UserNotFoundException { 
            RegistrationDto registrationDto = this.registrationService
                    .getUserByEmail(email);
            
            return registrationDto;
       
    }
}
