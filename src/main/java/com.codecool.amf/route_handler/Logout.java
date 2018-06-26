package com.codecool.amf.route_handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class Logout {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    protected String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
