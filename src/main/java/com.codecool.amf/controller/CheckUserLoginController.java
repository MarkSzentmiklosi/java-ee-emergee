package com.codecool.amf.controller;

import com.codecool.amf.service.AuthService;
import com.codecool.amf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CheckUserLoginController {

    @Autowired
    AuthService authManager;
    @Autowired
    UserService userService;

    @PostMapping(value = "/check_user_login")
    public String checkUserLogin(@RequestParam(name = "email") String email,
                                 @RequestParam(name = "password") String password,
                                 HttpSession session) {

        return authManager.handleCheckUserLoginPost(email, password, session);
    }
}
