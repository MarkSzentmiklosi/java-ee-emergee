package com.codecool.amf.service;

import com.codecool.amf.model.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class IndexService {

    public String handleIndexGet(HttpSession session) {
        if (session.getAttribute("partnerId") != null) {
            return "partner_index";
        }

        if (session.getAttribute("user") == null) {
            return "login";
        } else {
            User user = (User) session.getAttribute("user");
            if (user.getAddress() != null) {
                return "index";
            } else {
                return "update-profile";
            }

        }
    }
}
