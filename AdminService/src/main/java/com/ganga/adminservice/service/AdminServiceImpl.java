package com.ganga.adminservice.service;

import com.ganga.adminservice.Repository.AdminRepository;
import com.ganga.adminservice.dto.RegisterUserDto;
import com.ganga.adminservice.dto.UpdateUserDto;
import com.ganga.adminservice.dto.UserDetailsDto;
import com.ganga.adminservice.dto.UserLoginDetailsDTO;
import com.ganga.adminservice.entity.Admin;
import com.ganga.adminservice.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
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
    public void registerUser(RegisterUserDto registerUserDto) throws NonUniqueResultException, IllegalArgumentException {
        mapper = utility.getDozerBeanMapper();
        Optional<Admin> optional = Optional.ofNullable(adminRepository.findByUsername(registerUserDto.getUserName()));
        if(optional.isPresent()){
            throw new IllegalArgumentException("User already exists");
        }
        registerUserDto.setPassword(BCrypt.hashpw(registerUserDto.getPassword(), BCrypt.gensalt()));
        Admin user = mapper.map(registerUserDto, Admin.class);
        user.setLoginAttempt(0);
        user.setRoles(Arrays.asList("ROLE_USER"));
        user.setStatus('Y');
        adminRepository.save(user);
    }

    @Override
    public void updateUser(UpdateUserDto updateUserDto, String userName) throws IllegalArgumentException {
        Admin user = adminRepository.findByUsername(userName);
        if(user == null){
            throw new IllegalArgumentException("Provided user doesn't exists");
        }
        if(updateUserDto.getPassword() != null){
            user.setPassword(BCrypt.hashpw(updateUserDto.getPassword(), BCrypt.gensalt()));
        }
        if(updateUserDto.getEmailAddress() != null){
            user.setEmailAddress(updateUserDto.getEmailAddress());
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
