package com.codecool.amf.route_handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class Registration {

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registartion(HttpSession session,
                               @RequestParam(name = "registration_email") String email,
                               @RequestParam(name = "registration_password") String password) {

        session.setAttribute("password", password);
        session.setAttribute("email", email);
        return "update-profile";
    }

}
