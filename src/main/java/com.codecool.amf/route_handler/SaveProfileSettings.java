package com.codecool.amf.route_handler;

import com.codecool.amf.Controller.ProfileController;
import com.codecool.amf.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveProfileSettings extends HttpServlet {

    ProfileController profileController;

    public SaveProfileSettings(ProfileController profileController) {
        this.profileController = profileController;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String dataType = req.getParameter("dataType");
        String input = req.getParameter("data");
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        profileController.updateUserProfile(currentUser, dataType, input);

    }

}
