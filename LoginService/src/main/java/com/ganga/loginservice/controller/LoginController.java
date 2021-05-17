package com.ganga.loginservice.controller;

import com.ganga.loginservice.dto.LoginRequestDto;
import com.ganga.loginservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request){
        try{
            String token = loginService.login(loginRequestDto, request);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(produces = "application/json")
    public String test(){
        return "Hi from Login Service";
    }
}
