package com.codecool.amf.service;

import com.codecool.amf.model.User;
import com.codecool.amf.route_handler.IdTokenVerifierAndParser;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

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

}
