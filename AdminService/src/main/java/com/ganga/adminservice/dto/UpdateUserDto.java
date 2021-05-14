package com.ganga.adminservice.dto;

import lombok.Data;

@Data
public class UpdateUserDto {
    private String username;
    private String emailAddress;
    private String password;
}
