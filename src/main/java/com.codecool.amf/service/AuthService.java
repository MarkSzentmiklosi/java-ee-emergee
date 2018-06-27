package com.codecool.amf.service;

import com.codecool.amf.model.User;
import com.codecool.amf.route_handler.IdTokenVerifierAndParser;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    public String handleRedirectGoogleUserPost(HttpSession session, String idToken) {
        try {
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);

            if (isUserExist(session, payLoad)) {
                return "redirect:/";
            } else {
                return signUpGoogleUser(session, payLoad);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private boolean isUserExist(HttpSession session, GoogleIdToken.Payload payLoad) {

        String email = payLoad.getEmail();
        Optional<User> loginUser = Optional.ofNullable(userService.getUserByEmail(email));

        if (loginUser.isPresent()) {
            session.setAttribute("user", loginUser.get());
            return true;
        }

        return false;
    }

    private String signUpGoogleUser(HttpSession session, GoogleIdToken.Payload payLoad) {

        String name = (String) payLoad.get("name");
        String email = payLoad.getEmail();
        session.setAttribute("userName", name);
        session.setAttribute("email", email);

        return "update-profile";
    }

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
