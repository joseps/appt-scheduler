package com.redox.apptScheduler.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Provider Entity
 */
@Entity
public class Provider extends Person implements Serializable {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
