package com.redox.apptScheduler.entity;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Base Entity, implements created and updated dates for all entities
 */
@MappedSuperclass
public class BaseEntity {

    private Date createdDate;
    private Date updatedDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
