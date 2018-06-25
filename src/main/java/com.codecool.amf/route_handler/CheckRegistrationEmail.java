package com.codecool.amf.route_handler;

import com.codecool.amf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CheckRegistrationEmail{

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/check_registration_email",method = RequestMethod.POST)
    public String checkEmail(@RequestParam(name = "email") String email) {
        String response = "newEmail";

        if (userService.getUserByEmail(email) != null) {
            response = "existingEmail";
        }
        return response;
    }
}

