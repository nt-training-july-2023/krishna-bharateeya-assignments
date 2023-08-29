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
import com.nucleusteq.assessmentPlatform.entity.Registration;
import com.nucleusteq.assessmentPlatform.exception.UserEmailDomainException;
import com.nucleusteq.assessmentPlatform.exception.UserNotFoundException;
import com.nucleusteq.assessmentPlatform.service.RegistrationService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping(path = "/save")
    public String saveUser(@RequestBody RegistrationDto user)
            throws UserEmailDomainException {

        return registrationService.addUser(user);

    }

    @PostMapping(path = "/login")
    public Map<String, String> loginUser(@RequestBody RegistrationDto user)
            throws UserNotFoundException {
        Map<String, String> response = registrationService.loginUser(user);
        return response;
    }

    @GetMapping("/get/{id}")
    public RegistrationDto getUserById(@PathVariable("id") int userId)
            throws UserNotFoundException {
        RegistrationDto registrationDto = this.registrationService
                .getUserById(userId);
        return registrationDto;
    }

}
