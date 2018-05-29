package main.java.com.codecool.amf.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    private int id;
    private String phoneNumber;
    private String name;
    private String email;
    private Address address;
    private String idCardNum;
    private List<HRequest> requests;

}
