package com.ganga.gateway.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserLoginDetailsDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Character status;
    @NotNull
    private Integer loginAttempt;
    @NotNull
    private List<String> roles;
}
