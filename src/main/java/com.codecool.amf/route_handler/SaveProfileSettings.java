package com.codecool.amf.route_handler;

import com.codecool.amf.model.User;
import com.codecool.amf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

@RestController
public class SaveProfileSettings extends HttpServlet {

    @Autowired
    UserService userService;

    @PostMapping(value = "/saveProfile")
    public void saveProfileSettings(HttpSession session,
                                    @RequestParam(name = "dataType") String dataType,
                                    @RequestParam(name = "data") String input) {

        User currentUser = (User) session.getAttribute("user");
        userService.updateUserProfile(currentUser, dataType, input);
        User modifiedUser = userService.getUserByEmail(currentUser.getEmail());

        session.setAttribute("user", modifiedUser);

    }
}
