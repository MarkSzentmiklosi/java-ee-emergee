package com.codecool.amf.route_handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class Index {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderIndex(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "login";
        } else {
            return "index";
        }
    }

}
