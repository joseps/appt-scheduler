package com.redox.apptScheduler.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Patient Entity
 */
@Entity
public class Patient extends Person implements Serializable {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
