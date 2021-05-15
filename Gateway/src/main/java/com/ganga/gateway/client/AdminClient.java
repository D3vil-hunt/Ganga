package com.ganga.gateway.client;

import com.ganga.gateway.dto.UserLoginDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("admin-service")
@RequestMapping("/admin")
public interface AdminClient {

    @GetMapping(value = "/get_login_details/{userName}")
    UserLoginDetailsDTO getLoginDetails(@PathVariable("userName") String userName);
}
