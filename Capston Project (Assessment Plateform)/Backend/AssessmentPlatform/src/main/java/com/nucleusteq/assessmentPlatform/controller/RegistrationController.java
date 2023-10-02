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
import com.nucleusteq.assessmentPlatform.utility.RegistrationLoggerMessage;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

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
    public final ResponseEntity<SuccessResponse> saveUser(
            @Valid @RequestBody final RegistrationDto user) {
        LOGGER.info(RegistrationLoggerMessage.SAVE_USER);
        SuccessResponse response = registrationService.addUser(user);
        LOGGER.info(RegistrationLoggerMessage.REGISTERED_SUCCESSFULLY);
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
        LOGGER.info(RegistrationLoggerMessage.LOGIN_REQUEST, user.getEmail());

        Map<String, String> response = registrationService.loginUser(user);
      LOGGER.info(
      RegistrationLoggerMessage.USER_LOGGED_IN_SUCCESSFULLY, user.getEmail());

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
        LOGGER.info(RegistrationLoggerMessage.USER_ID_REQUEST, userId);
        RegistrationDto registrationDto = this.registrationService
                .getUserById(userId);
        LOGGER.info(RegistrationLoggerMessage.USER_FOUND_SUCCESSFULLY, userId);
        return new ResponseEntity<>(registrationDto, HttpStatus.OK);
    }

    /**
     * Retrieves all user.
     * @return The RegistrationDto object representing the retrieved user.
     * found.
     */
    @GetMapping("/get/all")
    public final ResponseEntity<List<RegistrationDto>> getAllUsers() {
        LOGGER.info(RegistrationLoggerMessage.GET_ALL_USERS);
        List<RegistrationDto> result =
                registrationService.getAllRegistrations();
        LOGGER.info(RegistrationLoggerMessage.GET_ALL_USERS_SUCCESS);
        return new ResponseEntity<>(result, HttpStatus.OK);
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
        LOGGER.info(RegistrationLoggerMessage.USER_BY_EMAIL);
        RegistrationDto registrationDto = this.registrationService
                .getUserByEmail(email);
        LOGGER.info(RegistrationLoggerMessage.USER_BY_EMAIL_SUCCESS);
        return new ResponseEntity<>(registrationDto, HttpStatus.OK);
    }
}
