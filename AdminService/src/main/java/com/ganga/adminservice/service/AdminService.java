package com.ganga.adminservice.service;

import com.ganga.adminservice.dto.RegisterUserDto;
import com.ganga.adminservice.dto.UpdateUserDto;
import com.ganga.adminservice.dto.UserDetailsDto;
import com.ganga.adminservice.dto.UserLoginDetailsDTO;

public interface AdminService {
    void registerUser(RegisterUserDto registerUserDto);
    void updateUser(UpdateUserDto updateUserDto, String userName) throws IllegalArgumentException;
    UserDetailsDto getUserDetails(String userName);
    UserLoginDetailsDTO getUserLoginDetails(String userName);
}
