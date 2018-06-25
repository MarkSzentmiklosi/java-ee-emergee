package com.codecool.amf.route_handler;

import com.codecool.amf.model.User;
import com.codecool.amf.service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class GoogleLogin {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/glogin", method = RequestMethod.POST)
    public String redirectGoogleUser(HttpServletRequest req) {
        try {
            String idToken = req.getParameter("id_token");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);


            if (existingUser(req, payLoad)) {
                return "redirect:/";
            } else {
                return signUpGoogleUser(req, payLoad);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }
    }

    private String signUpGoogleUser(HttpServletRequest req, GoogleIdToken.Payload payLoad) throws IOException {
        String name = (String) payLoad.get("name");
        String email = payLoad.getEmail();
        HttpSession session = req.getSession();
        session.setAttribute("userName", name);
        session.setAttribute("email", email);
        return "update-profile";
    }

    private boolean existingUser(HttpServletRequest req, GoogleIdToken.Payload payLoad) throws IOException {
        try {

            String email = payLoad.getEmail();
            User loginUser = userService.getUserByEmail(email);
            User user = new User(loginUser.getName(), loginUser.getEmail(), loginUser.getPhoneNumber(), loginUser.getIdCardNum(), loginUser.getAddress());
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}

