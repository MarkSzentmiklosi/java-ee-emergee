package com.codecool.amf.controller;

import com.codecool.amf.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class GoogleLoginController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/glogin")
    public String redirectGoogleUser(@RequestParam(name = "id_token") String idToken,
                                     Model model) {

        return authService.handleRedirectGoogleUserPost(idToken, model);
    }

}

