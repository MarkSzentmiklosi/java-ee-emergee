package com.codecool.amf.service;

import com.codecool.amf.model.Address;
import com.codecool.amf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    PasswordService passwordService;

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    public String handleRegistrationPost(HttpSession session, String email, String password) {
        User newUser = new User(email, passwordService.hashPassword(password));
        session.setAttribute("user", newUser);

        return "update-profile";
    }

    public String handleNewUserProfilePost(HttpSession session, Map<String, String> requestParams) {

        String country = requestParams.get("country");
        String city = requestParams.get("city");
        String zipCode = requestParams.get("zipCode");
        String street = requestParams.get("street");
        String houseNum = requestParams.get("houseNum");

        User user = (User) session.getAttribute("user");
        Optional<Address> address = Optional.ofNullable(addressService.getHomeAddress(country, city, zipCode, street, houseNum));

        if (!address.isPresent()) {
            user.setAddress(new Address(country,city,zipCode,street,houseNum));
        } else {
            user.setAddress(address.get());
        }

        user.setIdCardNum(requestParams.get("idCard"));
        user.setName(requestParams.get("name"));
        user.setPhoneNumber(requestParams.get("phoneNumber"));
        userService.saveUser(user);
        session.setAttribute("user", user);

        return "redirect:/";
    }
}
