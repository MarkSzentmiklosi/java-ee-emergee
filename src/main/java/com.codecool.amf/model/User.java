package com.codecool.amf.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String name;
    @Column(unique = true)
    private String email;
    @ManyToOne
    private Address address;
    @Column(unique = true,name = "id_card")
    private String idCardNum;
    @OneToMany(mappedBy = "user")
    private List<HRequest> requests = new ArrayList<>();
    @Column(name="hash")
    private String passwordHash;

    public User(){}

    public User(String phoneNumber, String name, String email, Address address, String idCardNum, List<HRequest> requests) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.address = address;
        this.idCardNum = idCardNum;
        this.requests = requests;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public int getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public List<HRequest> getRequests() {
        return requests;
    }
}
