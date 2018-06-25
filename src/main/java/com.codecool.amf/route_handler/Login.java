package com.codecool.amf.route_handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class Login {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session, Model model) {

        if (session.getAttribute("user") != null) {
            return "redirect:/";
        } else {
            model.addAttribute("error", false);
            return "login";
        }
    }

}
