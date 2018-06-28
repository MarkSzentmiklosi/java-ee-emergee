package com.codecool.amf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    protected String logout(HttpSession session) {

        if (session.getAttribute("partnerId") != null) {
            session.invalidate();
            return "redirect:/partner-login";
        }

        session.invalidate();
        return "redirect:/";
    }
}
