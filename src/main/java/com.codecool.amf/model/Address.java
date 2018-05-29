package com.codecool.amf.model;

import javax.persistence.*;

@Entity
@Table(name = "home_addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "zip_code")
    private String zipCode;
    private String city;
    private String country;
    private String street;
    @Column(name = "house_number")
    private int houseNum;

    public Address() {
    }


    public Address(String zipCode, String city, String country, String street, int houseNum) {
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.street = street;
        this.houseNum = houseNum;
    }
}
