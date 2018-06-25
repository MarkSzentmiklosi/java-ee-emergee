package com.codecool.amf.repository;

import com.codecool.amf.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address getAddressByCountryAndCityAndZipCodeAndStreetAndHouseNum(String country, String city, String zipCode, String street, String houseNum);
}
