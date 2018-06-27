package com.codecool.amf.service;

import com.codecool.amf.model.Address;
import com.codecool.amf.model.User;
import com.codecool.amf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressService addressService;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void updateUserName(User user, String username) {
        user.setName(username);
        userRepository.save(user);
    }

    public void updateIdCardNum(User user, String cardNum) {
        user.setIdCardNum(cardNum);
        userRepository.save(user);
    }

    public void updatePhoneNumber(User user, String phoneNumber) {
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
    }

    public void updateUserProfile(User currentUser, String dataType, String input) {
        User user = getUserByEmail(currentUser.getEmail());
        Address address = user.getAddress();

        switch (dataType) {
            case "name":
                updateUserName(user, input);
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
                updatePhoneNumber(user, input);
                break;
            case "idCardNum":
                updateIdCardNum(user, input);
                break;
        }
    }
}
