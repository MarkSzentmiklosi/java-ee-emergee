package com.codecool.amf.route_handler.edit_profile;

import com.codecool.amf.jpa.QueryManager;
import com.codecool.amf.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveHouseNum extends HttpServlet {
    QueryManager queryManager;

    public SaveHouseNum(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String input = req.getParameter("houseNum");
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        queryManager.updateHouseNum(currentUser, input);
        resp.getWriter().write("success");
    }
}
