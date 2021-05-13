package com.ganga.adminservice.startup;

import com.ganga.adminservice.Repository.AdminRepository;
import com.ganga.adminservice.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

@Component
public class ApplicationStartup {

    private final AdminRepository adminRepository;
    private final StartupProperties startupProperties;

    @Autowired
    public ApplicationStartup(AdminRepository adminRepository, StartupProperties startupProperties) {
        this.adminRepository = adminRepository;
        this.startupProperties = startupProperties;
    }

     @Bean
    public CommandLineRunner loadData(){
         System.out.println("--------------Hi from admin--------------");
        return (args) -> {
            List<Admin> admins = (List<Admin>) adminRepository.findAll();
            if(ObjectUtils.isEmpty(admins)){
                adminRepository.save(Admin.builder()
                        .username(startupProperties.getUsername())
                        .password(BCrypt.hashpw(startupProperties.getPassword(), BCrypt.gensalt()))
                        .roles(Arrays.asList("ADMIN"))
                        .status(startupProperties.getStatus())
                        .build());
            }
        };
     }
}
