package com.codecool.amf.model;

import javax.persistence.*;

@Entity
@Table(name = "request_location")
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

    public int getId() {
        return id;
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
