package com.codecool.amf.service;

import org.springframework.stereotype.Service;

import static org.mindrot.jbcrypt.BCrypt.checkpw;
import static org.mindrot.jbcrypt.BCrypt.gensalt;
import static org.mindrot.jbcrypt.BCrypt.hashpw;

@Service
public class PasswordService {

    private static int workload = 12;

    public String hashPassword(String password_plaintext) {
        String salt = gensalt(workload);
        String hashed_password = hashpw(password_plaintext, salt);

        return (hashed_password);
    }

    public boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = checkpw(password_plaintext, stored_hash);

        return (password_verified);
    }
}
