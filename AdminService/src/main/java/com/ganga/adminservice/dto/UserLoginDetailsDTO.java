package com.ganga.adminservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginDetailsDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
