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
    private HelpRequest request;


    public Location() {
    }

    public Location(String country, String city, String zipCode, String street, String houseNum) {
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.houseNum = houseNum;
    }
}
