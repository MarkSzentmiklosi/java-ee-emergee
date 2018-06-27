package com.codecool.amf.controller;

import com.codecool.amf.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    @Autowired
    AuthService authService;

    @PostMapping("/registration")
    public String registration(HttpSession session,
                               @RequestParam(name = "registration_email") String email,
                               @RequestParam(name = "registration_password") String password) {

        return authService.handleRegistrationPost(session,email,password);
    }

}
