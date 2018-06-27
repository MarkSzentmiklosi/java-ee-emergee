package com.codecool.amf.controller;

import com.codecool.amf.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class NewUserProfileController {

    @Autowired
    AuthService authService;

    @PostMapping("/updateProfile")
    public String createNewUserProfile(@RequestParam Map<String, String> requestParams,
                                       HttpSession session) {

        authService.handleNewUserProfilePost(session, requestParams);

        return "redirect:/";

    }
}
