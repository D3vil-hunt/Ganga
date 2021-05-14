package com.ganga.loginservice.service;

import com.ganga.loginservice.client.AdminClient;
import com.ganga.loginservice.dto.LoginRequestDto;
import com.ganga.loginservice.dto.UserLoginDetailsDTO;
import com.ganga.loginservice.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final AdminClient adminClient;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public LoginServiceImpl(AdminClient adminClient, JwtTokenProvider jwtTokenProvider) {
        this.adminClient = adminClient;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginRequestDto loginRequestDto, HttpServletRequest request) throws IllegalArgumentException{
        Optional<UserLoginDetailsDTO> data = Optional.of(adminClient.getLoginDetails(loginRequestDto.getUserName()));
        String jwtToken;
        if (data.isPresent()){
            UserLoginDetailsDTO userLoginDetailsDTO = data.get();
            if(BCrypt.checkpw(loginRequestDto.getPassword(), userLoginDetailsDTO.getPassword())){
                jwtToken = jwtTokenProvider.createToken(loginRequestDto.getUserName(),request);
            }else{
                throw new IllegalArgumentException("Invalid password");
            }
        }else{
            throw new IllegalArgumentException("The provided username is invalid");
        }
        return jwtToken;
    }
}
