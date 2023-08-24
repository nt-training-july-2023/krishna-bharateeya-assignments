package com.nucleusteq.assessmentPlatform.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleusteq.assessmentPlatform.entity.Registration;
import com.nucleusteq.assessmentPlatform.service.RegistrationService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@PostMapping(path = "/save")
	public String saveUser(@RequestBody Registration user) {

		return registrationService.addUser(user);

	}

	@PostMapping(path = "/login")
	public Map<String, String> loginUser(@RequestBody Registration user) {
		Map<String, String> response = registrationService.loginUser(user);
		return response;
	}
}
