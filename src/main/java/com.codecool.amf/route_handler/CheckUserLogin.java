package com.codecool.amf.route_handler;

import com.codecool.amf.authenticator.AuthenticationManager;
import com.codecool.amf.model.User;
import com.codecool.amf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CheckUserLogin {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/check_user_login", method = RequestMethod.POST)
    public String checkUserLogin(@RequestParam(name = "email") String email,
                                 @RequestParam(name = "password") String password,
                                 HttpSession session) {

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
        return authenticationManager.checkPassword(inputPassword, userPassword);
    }
}
