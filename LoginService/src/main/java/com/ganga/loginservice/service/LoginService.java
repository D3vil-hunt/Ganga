package com.ganga.loginservice.service;

import com.ganga.loginservice.dto.LoginRequestDto;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    String login(LoginRequestDto loginRequestDto , HttpServletRequest request) throws IllegalArgumentException;
}
