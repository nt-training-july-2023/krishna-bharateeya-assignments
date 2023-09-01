package com.nucleusteq.assessmentPlatform.controller;

import java.util.Map;

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
     * Registers a new user.
     *
     * @param user The RegistrationDto object containing user information.
     * @return A message indicating the result of the user registration.
     * @throws UserEmailDomainException If the user's email domain is invalid.
     */
    @PostMapping(path = "/save")
    public final String saveUser(@RequestBody final RegistrationDto user)
            throws UserEmailDomainException {

        return registrationService.addUser(user);

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
        Map<String, String> response = registrationService.loginUser(user);
        return response;
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
            @PathVariable("id")final int userId)
            throws UserNotFoundException {
        RegistrationDto registrationDto = this.registrationService
                .getUserById(userId);
        return registrationDto;
    }

}
