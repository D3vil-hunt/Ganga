package com.ganga.adminservice.controller;

import com.ganga.adminservice.dto.RegisterUserDto;
import com.ganga.adminservice.dto.UpdateUserDto;
import com.ganga.adminservice.dto.UserDetailsDto;
import com.ganga.adminservice.dto.UserLoginDetailsDTO;
import com.ganga.adminservice.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@Slf4j
public class controller {
    private final AdminService adminService;

    @Autowired
    public controller(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/test", produces = "application/json")
    public String hello(){
        return "hello";
    }

    @PostMapping(consumes = "application/json", value = "/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto){
        try{
            adminService.registerUser(registerUserDto);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("User already exists.", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(consumes = "application/json", value = "/update/{userName}")
    public void updateUser(@RequestBody UpdateUserDto updateUserDto, @PathVariable("userName") String userName){
        adminService.updateUser(updateUserDto,userName);
    }

    @GetMapping(value = "/get_user/{userName}", produces = "application/json")
    public UserDetailsDto getUser(@PathVariable("userName") String userName){
        return adminService.getUserDetails(userName);
    }

    @GetMapping(value = "/get_login_details/{userName}", produces = "application/json")
    public UserLoginDetailsDTO getUserLoginDetails(@PathVariable("userName") String userName){
        return adminService.getUserLoginDetails(userName);
    }
}
