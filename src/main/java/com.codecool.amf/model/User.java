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

    @ManyToOne(cascade = CascadeType.MERGE)
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<HelpRequest> requests = new ArrayList<>();

    @Column(name = "password")
    private String passwordHash;


    public User() {
    }

    public User(String name, String email, String phoneNumber, String idCardNum, Address address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idCardNum = idCardNum;
        this.address = address;
    }

    public User(String name, String email, String phoneNumber, String idCardNum, Address address, String passwordHash) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idCardNum = idCardNum;
        this.address = address;
        this.passwordHash = passwordHash;
    }

    public User(String email, String password) {

        this.email = email;
        this.passwordHash = password;
    }

    public void addRequest(HelpRequest request) {
        requests.add(request);
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

    public List<HelpRequest> getRequests() {
        return requests;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
