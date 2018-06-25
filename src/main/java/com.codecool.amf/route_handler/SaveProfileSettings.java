package com.codecool.amf.route_handler;

import com.codecool.amf.model.Address;
import com.codecool.amf.model.User;
import com.codecool.amf.service.AddressService;
import com.codecool.amf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

@Controller
public class SaveProfileSettings extends HttpServlet {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
    public void saveProfileSettings(HttpSession session,
                                    @RequestParam(name = "dataType") String dataType,
                                    @RequestParam(name = "data") String input) {

        User currentUser = (User) session.getAttribute("user");
        updateUserProfile(currentUser, dataType, input);

        User modifiedUser = userService.getUserByEmail(currentUser.getEmail());
        session.setAttribute("user", modifiedUser);

    }

    private void updateUserProfile(User currentUser, String dataType, String input) {
        User user = userService.getUserByEmail(currentUser.getEmail());
        Address address = user.getAddress();

        switch (dataType) {
            case "name":
                userService.updateUserName(user, input);
                break;
            case "country":
                addressService.updateCountry(address, input);
                break;
            case "city":
                addressService.updateCity(address, input);
                break;
            case "zipCode":
                addressService.updateZip(address, input);
                break;
            case "street":
                addressService.updateStreet(address, input);
                break;
            case "houseNumber":
                addressService.updateHouseNumber(address, input);
                break;
            case "phoneNumber":
                userService.updatePhoneNumber(user, input);
                break;
            case "idCardNum":
                userService.updateIdCardNum(user, input);
                break;
        }
    }
}
