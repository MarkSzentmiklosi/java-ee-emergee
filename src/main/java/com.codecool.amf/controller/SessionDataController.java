package com.codecool.amf.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SessionDataController {

    @PostMapping("/partner_session_id")
    public String getPartnerIdFromSession(HttpSession session) {

        return new JSONObject().put("partner_id", session.getAttribute("partnerId")).toString();
    }
}
