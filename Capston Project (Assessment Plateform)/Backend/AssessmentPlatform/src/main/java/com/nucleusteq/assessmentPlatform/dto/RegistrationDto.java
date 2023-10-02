package com.nucleusteq.assessmentPlatform.dto;

import com.nucleusteq.assessmentPlatform.utility.ValidationMessage;

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
    @NotBlank(message = ValidationMessage.FIRST_NAME_EMPTY)
    private String firstName;

    /**
     * The last name of the user.
     */
    @NotBlank(message = ValidationMessage.LAST_NAME_EMPTY)
    private String lastName;

    /**
     * The mobile number of the user.
     */
    @NotBlank(message = ValidationMessage.MOBILE_NUMBER_EMPTY)
    @Pattern(regexp = "^\\d{10}$",
            message = ValidationMessage.MOBILE_NUMBER_PATTERN)
    private String mobileNumber;

    /**
     * The role of the user.
     */
    private String userRole;

    /**
     * The email address of the user.
     */
    @NotBlank(message = ValidationMessage.EMAIL_EMPTY)
    @Pattern(regexp = "^[A-Z0-9a-z.+_-]+@nucleusteq[.]com$",
            message = ValidationMessage.EMAIL_DOMAIN_INVALID)
    private String email;

    /**
     * The password of the user.
     */
    @NotBlank(message = ValidationMessage.PASSWORD_EMPTY)
    @Size(min = 4, message = ValidationMessage.PASSWORD_SIZE)
    private String password;
}
