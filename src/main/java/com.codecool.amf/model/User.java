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

    private String name;

    @Column(unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(unique = true, name = "id_card")
    private String idCardNum;

    @ManyToOne
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<HRequest> requests = new ArrayList<>();


    public User() {
    }

    public User(String name, String email, String phoneNumber, String idCardNum, Address address, List<HRequest> requests) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idCardNum = idCardNum;
        this.address = address;
        this.requests = requests;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<HRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<HRequest> requests) {
        this.requests = requests;
    }
}
