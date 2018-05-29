package com.codecool.amf.model;

import javax.persistence.*;
import java.util.List;

public class User {
    private int id;
    private String phoneNumber;
    private String name;
    private String email;
    private HomeAddress address;
    private String idCardNum;
    private List<HRequest> requests;

}
