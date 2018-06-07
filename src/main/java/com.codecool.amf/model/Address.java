package com.codecool.amf.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "home_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String country;

    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    private String street;

    @Column(name = "house_number")
    private String houseNum;

    @OneToMany(mappedBy = "address")
    private List<User> users = new ArrayList<>();


    public Address() {
    }

    public Address(String country, String city, String zipCode, String street, String houseNum) {
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.houseNum = houseNum;
    }

    @Override
    public String toString() {
        return this.country + " " + this.zipCode + " " + this.city + " " + this.street + " "  + this.houseNum;
    }
}
