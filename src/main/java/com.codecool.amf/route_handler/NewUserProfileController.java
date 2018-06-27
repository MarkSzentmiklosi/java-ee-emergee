package com.codecool.amf.route_handler;

import com.codecool.amf.model.Address;
import com.codecool.amf.model.User;
import com.codecool.amf.service.AddressService;
import com.codecool.amf.service.AuthService;
import com.codecool.amf.service.PasswordService;
import com.codecool.amf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

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
