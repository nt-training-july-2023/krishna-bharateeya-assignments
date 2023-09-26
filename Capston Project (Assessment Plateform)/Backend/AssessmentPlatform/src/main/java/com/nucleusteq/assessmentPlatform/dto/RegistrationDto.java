package com.nucleusteq.assessmentPlatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing user registration information.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {

    /**
     * The ID of the user.
     */
    private int userId;

    /**
     * The first name of the user.
     */
    @NotBlank(message = "First name cannot be empty.")
    private String firstName;

    /**
     * The last name of the user.
     */
    @NotBlank(message = "Last Name cannot be empty.")
    private String lastName;

    /**
     * The mobile number of the user.
     */
    @NotBlank(message = "Mobile Number cannot be empty.")
    @Pattern(regexp="^\\d{10}$",message="Mobile Number should be only 10 digit.")
    private String mobileNumber;

    /**
     * The role of the user.
     */
    private String userRole;

    /**
     * The email address of the user.
     */
    @NotBlank(message = "Email cannot be empty.")
    @Pattern(regexp = "^[A-Z0-9a-z.+_-]+@nucleusteq[.]com$",
    message = "Email Domain is not Valid")
    private String email;

    /**
     * The password of the user.
     */
    @NotBlank(message = "Password cannot be empty.")
    private String password;
}
