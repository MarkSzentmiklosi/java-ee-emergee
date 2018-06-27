package com.codecool.amf.controller;

import com.codecool.amf.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CheckRegistrationEmail {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/check_registration_email")
    public String checkEmail(@RequestParam(name = "email") String email) {
        return authService.handleCheckRegistrationEmailPost(email);
    }
}

