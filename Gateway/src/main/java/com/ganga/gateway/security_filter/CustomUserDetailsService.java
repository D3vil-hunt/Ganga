package com.ganga.gateway.security_filter;

import com.ganga.gateway.client.AdminClient;
import com.ganga.gateway.dto.UserLoginDetailsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminClient adminClient;


    @Override
    public UserDetails loadUserByUsername(String userName) {
        log.info("Into CustomUserDetailsService loadUserByUsername");
        UserLoginDetailsDTO adminResponseDTO = adminClient.getLoginDetails(userName);
        if(Objects.isNull(adminResponseDTO)){
            new UsernameNotFoundException("Username:" + userName + " not found");
        }
        List<GrantedAuthority> grantedAuthorities = adminResponseDTO.getRoles()
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(String.join("-", userName),
                adminResponseDTO.getPassword(), true, true, true,
                true, grantedAuthorities);
    }
}
