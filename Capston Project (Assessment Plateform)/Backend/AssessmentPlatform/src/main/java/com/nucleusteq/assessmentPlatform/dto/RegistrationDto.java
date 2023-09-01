package com.nucleusteq.assessmentPlatform.dto;

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
    private String firstName;

    /**
     * The last name of the user.
     */
    private String lastName;

    /**
     * The mobile number of the user.
     */
    private String mobileNumber;

    /**
     * The role of the user.
     */
    private String userRole;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;
}
