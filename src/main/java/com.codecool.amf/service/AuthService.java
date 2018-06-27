package com.codecool.amf.service;

import com.codecool.amf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    public String handleLoginGet(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        } else {
            model.addAttribute("error", false);
            return "login";
        }
    }

    public String handleCheckRegistrationEmailPost(String email) {
        String response = "newEmail";

        if (userService.getUserByEmail(email) != null) {
            response = "existingEmail";
        }
        return response;
    }

    public String handleCheckUserLoginPost(String email, String password, HttpSession session) {
        String response = "invalid";

        User user = userService.getUserByEmail(email);
        if (user != null) {
            String savedPassword = user.getPasswordHash();
            if (isPasswordMatch(password, savedPassword)) {
                session.setAttribute("user", user);
                response = "valid";
            }

        }

        return response;
    }

    private boolean isPasswordMatch(String inputPassword, String userPassword) {
        return passwordService.checkPassword(inputPassword, userPassword);
    }
}
