package com.nucleusteq.assessmentPlatform.dto;

import lombok.Data;

@Data
public class RegistrationDto {

    int userId;
    String firstName;
    String lastName;
    String mobileNumber;
    String userRole;
    String email;
    String password;
}
