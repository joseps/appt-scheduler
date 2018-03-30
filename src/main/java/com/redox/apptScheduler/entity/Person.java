package com.redox.apptScheduler.entity;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Person Entity
 */
@MappedSuperclass
public class Person extends BaseEntity implements Serializable {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
