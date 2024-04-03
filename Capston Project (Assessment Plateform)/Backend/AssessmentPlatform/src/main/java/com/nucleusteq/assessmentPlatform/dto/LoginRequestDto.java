package com.nucleusteq.assessmentPlatform.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a user's login request.
 * It contains the user's email and password for authentication.
 */
@Getter
@Setter
public class LoginRequestDto {
    /**
     * The user's email address.
     */
    @NotBlank(message = "Email is required.")
    private String email;

    /**
     * The user's password.
     */
    @NotBlank(message = "Password is required.")
    private String password;
}

