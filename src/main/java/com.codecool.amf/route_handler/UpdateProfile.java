package com.codecool.amf.route_handler;

import com.codecool.amf.jpa.PersistenceManager;
import com.codecool.amf.jpa.QueryManager;
import com.codecool.amf.model.Address;
import com.codecool.amf.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateProfile extends HttpServlet {

    private final PersistenceManager persistenceManager;
    private final QueryManager queryManager;


    public UpdateProfile(PersistenceManager persistenceManager, QueryManager queryManager) {
        this.persistenceManager = persistenceManager;
        this.queryManager = queryManager;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = req.getParameter("name");
        String email = (String) session.getAttribute("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String idCardNum = req.getParameter("idCard");

        String country = req.getParameter("country");
        String city = req.getParameter("city");

        String zipCode = req.getParameter("zipCode");

        String street = req.getParameter("street");

        String houseNum = req.getParameter("houseNum");

        Address newAddress;
        Address dbAddress = queryManager.getHomeAddress(country, city, zipCode, street, houseNum);

        if (dbAddress != null) {
            newAddress = dbAddress;

        } else {
            newAddress = new Address(country, city, zipCode, street, houseNum);
            persistenceManager.persistEntity(newAddress);
        }

        User newUser = new User(name, email, phoneNumber, idCardNum, newAddress);

        persistenceManager.persistEntity(newUser);

        session.setAttribute("user", newUser);
        resp.sendRedirect("/");
    }
}
