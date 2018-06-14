package com.codecool.amf.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "home_address")
public class Address {
    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

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

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNum() {
        return houseNum;
    }
}
