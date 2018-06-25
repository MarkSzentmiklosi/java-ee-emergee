package com.codecool.amf.service;

import com.codecool.amf.model.Address;
import com.codecool.amf.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public void updateCountry(Address address, String input) {
        address.setCountry(input);
        addressRepository.save(address);
    }

    public void updateCity(Address address, String input) {
        address.setCity(input);
        addressRepository.save(address);
    }

    public void updateZip(Address address, String input) {
        address.setZipCode(input);
        addressRepository.save(address);
    }

    public void updateStreet(Address address, String input) {
        address.setStreet(input);
        addressRepository.save(address);
    }

    public void updateHouseNumber(Address address, String input) {
        address.setHouseNum(input);
        addressRepository.save(address);
    }

    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

    public Address getHomeAddress(String country, String city, String zipCode, String street, String houseNum) {
        return addressRepository.getAddressByCountryAndCityAndZipCodeAndStreetAndHouseNum(country, city, zipCode, street, houseNum);
    }

}
