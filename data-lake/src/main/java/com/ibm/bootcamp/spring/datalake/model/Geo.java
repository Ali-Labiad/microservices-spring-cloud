package com.ibm.bootcamp.spring.datalake.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Geo
 */

@Entity
public class Geo {

    @Id
    private String userId;

    private long lat;
    private long lng;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLng() {
        return lng;
    }

    public void setLng(long lng) {
        this.lng = lng;
    }
}

