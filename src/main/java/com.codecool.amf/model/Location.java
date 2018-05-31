package com.codecool.amf.model;

import javax.persistence.*;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String country;

    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    private String street;

    @Column(name = "house_number")
    private String houseNum;

    @OneToOne(mappedBy = "location")
    private HRequest request;


    public Location() {
    }

    public Location(String country, String city, String zipCode, String street, String houseNum) {
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.houseNum = houseNum;
    }

    public Location(String country, String city, String zipCode, String street, String houseNum, HRequest request) {
        this(country, city, zipCode, street, houseNum);
        this.request = request;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public HRequest getRequest() {
        return request;
    }

    public void setRequest(HRequest request) {
        this.request = request;
    }
}
