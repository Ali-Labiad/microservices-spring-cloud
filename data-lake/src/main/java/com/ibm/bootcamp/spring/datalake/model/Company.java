package com.ibm.bootcamp.spring.datalake.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Company
 */

@Data
@Entity
public class Company {

    @Id
    private String userId;

    private String name;
    private String catchPhrase;
    private String bs;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}

