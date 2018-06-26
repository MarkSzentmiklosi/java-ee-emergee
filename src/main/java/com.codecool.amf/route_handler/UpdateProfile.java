package com.codecool.amf.route_handler;

import com.codecool.amf.authenticator.AuthenticationManager;
import com.codecool.amf.model.Address;
import com.codecool.amf.model.User;
import com.codecool.amf.service.AddressService;
import com.codecool.amf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UpdateProfile {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    String createNewUserProfile(HttpServletRequest req) {
        HttpSession session = req.getSession();

        String name = req.getParameter("name");
        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");
        String phoneNumber = req.getParameter("phoneNumber");
        String idCardNum = req.getParameter("idCard");

        String country = req.getParameter("country");
        String city = req.getParameter("city");

        String zipCode = req.getParameter("zipCode");

        String street = req.getParameter("street");

        String houseNum = req.getParameter("houseNum");

        Address newAddress;
        Address dbAddress = addressService.getHomeAddress(country, city, zipCode, street, houseNum);

        if (dbAddress != null) {
            newAddress = dbAddress;

        } else {
            newAddress = new Address(country, city, zipCode, street, houseNum);
            addressService.saveAddress(newAddress);
        }

        User newUser = new User(name, email, phoneNumber, idCardNum, newAddress);

        if (password != null) {
            newUser.setPasswordHash(authenticationManager.hashPassword(password));
        }

        userService.saveUser(newUser);

        session.setAttribute("user", newUser);
        return "redirect:/";

    }
}
