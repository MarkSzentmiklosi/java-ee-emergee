package com.codecool.amf.Controller;

import com.codecool.amf.jpa.QueryManager;
import com.codecool.amf.model.User;

public class ProfileController {

    QueryManager queryManager;

    public ProfileController(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    public void updateUserProfile(User currentUser, String dataType, String input) {
        User user = (User) queryManager.selectUserByEmail(currentUser.getEmail()).get(0);

        switch (dataType) {
            case "name":
                queryManager.updateUserName(user, input);
                break;
            case "country":
                queryManager.updateCountry(user, input);
                break;
            case "city":
                queryManager.updateCity(user, input);
                break;
            case "zipCode":
                queryManager.updateZipCode(user, input);
                break;
            case "street":
                queryManager.updateStreet(user, input);
                break;
            case "houseNumber":
                queryManager.updateHouseNum(user, input);
                break;
            case "phoneNumber":
                queryManager.updatePhoneNumber(user, input);
                break;
            case "idCardNum":
                queryManager.updateIdCardNum(user, input);
                break;
        }

    }
}
