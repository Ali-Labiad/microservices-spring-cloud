package com.ibm.bootcamp.spring.datalake.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Address
 */

@Entity
public class Address {

    @Id
    private String userId;

    private String street;
    private String suite;
    private String city;
    private String zipcode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

