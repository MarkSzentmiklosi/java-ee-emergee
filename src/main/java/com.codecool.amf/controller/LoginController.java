package com.codecool.amf.controller;

import com.codecool.amf.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    AuthService authService;

    @GetMapping(value = "/login")
    public String loginGet(HttpSession session, Model model) {

        return authService.handleLoginGet(session, model);
    }

    @PostMapping(value = "/login")
    public String loginPost(@RequestParam(name = "email") String email,
                            HttpSession session) {
        return authService.handleLoginPost(email, session);
    }
}
