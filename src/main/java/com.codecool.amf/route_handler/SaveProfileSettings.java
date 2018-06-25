//package com.codecool.amf.route_handler;
//
//import com.codecool.amf.controller.ProfileController;
//import com.codecool.amf.jpa.QueryManager;
//import com.codecool.amf.model.User;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class SaveProfileSettings extends HttpServlet {
//
//    ProfileController profileController;
//    QueryManager queryManager;
//
//    public SaveProfileSettings(ProfileController profileController, QueryManager queryManager) {
//        this.profileController = profileController;
//        this.queryManager = queryManager;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//        String dataType = req.getParameter("dataType");
//        String input = req.getParameter("data");
//        HttpSession session = req.getSession();
//        User currentUser = (User) session.getAttribute("user");
//        profileController.updateUserProfile(currentUser, dataType, input);
//        User modifiedUser = (User) queryManager.selectUserByEmail(currentUser.getEmail()).get(0);
//        session.setAttribute("user", modifiedUser);
//    }
//
//}
