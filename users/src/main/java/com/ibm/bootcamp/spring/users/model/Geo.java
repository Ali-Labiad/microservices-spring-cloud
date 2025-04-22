package com.ibm.bootcamp.spring.users.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Geo
 */

@Data
public class Geo {

    private BigDecimal lat;
    private BigDecimal lng;

}

