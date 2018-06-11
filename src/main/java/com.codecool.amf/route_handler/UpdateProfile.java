package com.codecool.amf.route_handler;

import com.codecool.amf.jpa.PersistenceManager;
import com.codecool.amf.model.Address;
import com.codecool.amf.model.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/updateProfile"})
public class UpdateProfile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String name = req.getParameter("name");
        String email = (String) session.getAttribute("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String idCardNum = req.getParameter("idCard");

        String country = req.getParameter("country");
        String city = req.getParameter("city");;
        String zipCode = req.getParameter("zipCode");;
        String street = req.getParameter("street");;
        String houseNum = req.getParameter("houseNum");;

        Address address = new Address(country, city,zipCode, street, houseNum);

        User newUser = new User(name, email, phoneNumber, idCardNum, address);

        //TODO: Persist data
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.persist(newUser);
        em.close();

        session.setAttribute("user", newUser);
        resp.sendRedirect("/");
    }
}
