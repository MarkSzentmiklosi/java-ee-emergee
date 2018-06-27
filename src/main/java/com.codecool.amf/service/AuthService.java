package com.codecool.amf.service;

import com.codecool.amf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthService {

    @Autowired
    PasswordService passwordService;

    public String handleRegistrationPost(HttpSession session, String email, String password) {
        User newUser = new User(email, passwordService.hashPassword(password));
        session.setAttribute("user", newUser);

        return "update-profile";
    }
}
