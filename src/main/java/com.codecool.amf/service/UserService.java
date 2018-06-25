package com.codecool.amf.service;

import com.codecool.amf.model.User;
import com.codecool.amf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    void saveUser(User user) {
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
}
