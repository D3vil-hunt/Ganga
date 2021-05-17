package com.ganga.adminservice.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class RegisterUserDto {
    @NotNull(message = "Username cannot be null")
    private String userName;
    @NotNull(message = "Password cannot be null")
    private String password;
    @Email(message = "Email should be valid")
    private String emailAddress;
}
