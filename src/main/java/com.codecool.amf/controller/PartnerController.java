package com.codecool.amf.controller;

import com.codecool.amf.service.AuthService;
import com.codecool.amf.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class PartnerController {

    @Autowired
    AuthService authService;

    @Autowired
    PartnerService partnerService;

    @PostMapping("/partner-login")
    public String partnerLogin(HttpSession session,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "password") String password) {

        return authService.handlePartnerLoginPost(email, password, session);
    }

    @GetMapping("/partner-login")
    public String displayPartnerLoginPage() {
        return "partner_login";
    }

    @GetMapping("/partner")
    public String partnerIndex(HttpSession session, Model model) {
        return partnerService.handleIndexGet(session, model);
    }
}
