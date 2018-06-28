package com.codecool.amf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SessionDataController {

    @PostMapping("/partner_session_id")
    public String getPartnerIdFromSession(HttpSession session) {
        return (String) session.getAttribute("partnerId");
    }
}
