package com.codecool.amf.service;

import com.codecool.amf.GoogleConfig.IdTokenVerifierAndParser;
import com.codecool.amf.model.Address;
import com.codecool.amf.model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
    IdTokenVerifierAndParser idTokenVerifierAndParser;

    @Autowired
    AddressService addressService;

    public String handleRedirectGoogleUserPost(String idToken, Model model) {
        try {
            GoogleIdToken.Payload payLoad = idTokenVerifierAndParser.getPayload(idToken);
            String email = payLoad.getEmail();
            String name = (String) payLoad.get("name");
            Optional<User> user = Optional.ofNullable(userService.getUserByEmail(email));

            if (!user.isPresent()) {
                return signUpGoogleUser(email, name, model);
            }

            if (user.isPresent() && user.get().getAddress() == null) {
                model.addAttribute("user", user.get());
                return "update-profile";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    private String signUpGoogleUser(String email, String name, Model model) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userService.saveUser(user);

        model.addAttribute("user", user);

        return "update-profile";
    }

    public String handleLoginGet(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        } else {
            model.addAttribute("error", false);
            return "login";
        }
    }

    public String handleCheckRegistrationEmailPost(String email) {
        String response = "newEmail";

        if (userService.getUserByEmail(email) != null) {
            response = "existingEmail";
        }
        return response;
    }

    public String handleCheckUserLoginPost(String email, String password, HttpSession session) {
        String response = "invalid";

        User user = userService.getUserByEmail(email);
        if (user != null) {
            String savedPassword = user.getPasswordHash();
            if (isPasswordMatch(password, savedPassword)) {
                session.setAttribute("user", user);
                response = "valid";
            }

        }

        return response;
    }

    private boolean isPasswordMatch(String inputPassword, String userPassword) {
        return passwordService.checkPassword(inputPassword, userPassword);
    }

    public String handleRegistrationPost(HttpSession session, String email, String password) {
        User newUser = new User(email, passwordService.hashPassword(password));
        session.setAttribute("user", newUser);

        userService.saveUser(newUser);

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
            user.setAddress(new Address(country, city, zipCode, street, houseNum));
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
