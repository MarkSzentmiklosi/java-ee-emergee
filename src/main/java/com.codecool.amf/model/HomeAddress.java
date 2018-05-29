package main.java.com.codecool.amf.model;

import javax.persistence.*;

@Entity
public class HomeAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "zipcode")
    private String zipCode;
    private String city;
    private String country;
    private String street;
    @Column(name = "housenum")
    private int houseNum;

    public HomeAddress() {
    }


    public HomeAddress(String zipCode, String city, String country, String street, int houseNum) {
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.street = street;
        this.houseNum = houseNum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    @Override
    public String toString() {
        return "Home Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", zip code='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house number='" + houseNum + '\'' +
                '}';
    }
}
