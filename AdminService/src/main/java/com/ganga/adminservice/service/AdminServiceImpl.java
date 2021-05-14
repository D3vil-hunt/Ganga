package com.ganga.adminservice.service;

import com.ganga.adminservice.Repository.AdminRepository;
import com.ganga.adminservice.dto.RegisterUserDto;
import com.ganga.adminservice.dto.UpdateUserDto;
import com.ganga.adminservice.dto.UserDetailsDto;
import com.ganga.adminservice.dto.UserLoginDetailsDTO;
import com.ganga.adminservice.entity.Admin;
import com.ganga.adminservice.util.Utility;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final Utility utility;
    private Mapper mapper;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, Utility utility) {
        this.adminRepository = adminRepository;
        this.utility = utility;
    }

    @Override
    public void registerUser(RegisterUserDto registerUserDto) {
        mapper = utility.getDozerBeanMapper();
        Admin user = mapper.map(registerUserDto, Admin.class);
        user.setLoginAttempt(0);
        user.setRoles(Arrays.asList("ROLE_USER"));
        adminRepository.save(user);
    }

    @Override
    public void updateUser(UpdateUserDto updateUserDto, String userName) throws IllegalArgumentException {
        Admin oldUserDetails = adminRepository.findByUsername(userName);
        if(oldUserDetails == null){
            throw new IllegalArgumentException("Provided user doesn't exists");
        }
        mapper = utility.getDozerBeanMapper();
        Admin user = mapper.map(updateUserDto, Admin.class);
        user.setStatus(oldUserDetails.getStatus());
        user.setRoles(oldUserDetails.getRoles());
        user.setLoginAttempt(oldUserDetails.getLoginAttempt());
        user.setUsername(oldUserDetails.getUsername());
        if (user.getPassword() == null){
            user.setPassword(oldUserDetails.getPassword());
        }
        adminRepository.save(user);
    }

    @Override
    public UserDetailsDto getUserDetails(String userName) {
        mapper = utility.getDozerBeanMapper();
        UserDetailsDto user = mapper.map(adminRepository.findByUsername(userName), UserDetailsDto.class);
        return user;
    }

    @Override
    public UserLoginDetailsDTO getUserLoginDetails(String userName) {
        mapper = utility.getDozerBeanMapper();
        UserLoginDetailsDTO user = mapper.map(adminRepository.findByUsername(userName), UserLoginDetailsDTO.class);
        return user;
    }
}
