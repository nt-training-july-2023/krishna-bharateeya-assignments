package com.nucleusteq.assessmentPlatform.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    @NotBlank(message = "Email is required.")
    private String email;
    
    @NotBlank(message = "Password is required.")
    private String password;
    
}
