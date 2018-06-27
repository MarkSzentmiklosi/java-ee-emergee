package com.codecool.amf.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class IndexService {

    public String handleIndexGet(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "login";
        } else {
            return "index";
        }
    }
}
