package com.ganga.adminservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Slf4j
public class controller {

    @GetMapping(value = "/test", produces = "application/json")
    public String hello(){
        return "hello";
    }
}
