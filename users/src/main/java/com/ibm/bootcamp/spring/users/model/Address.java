package com.ibm.bootcamp.spring.users.model;

import lombok.Data;

/**
 * Address
 */

@Data
public class Address {

    private String street;

    private String suite;

    private String city;

    private String zipcode;

    private Geo geo;

}

