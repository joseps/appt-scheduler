package com.redox.apptScheduler.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Department Entity
 */
@Entity
public class Department extends BaseEntity implements Serializable {
    @Id
    private Long id;

    private String departmentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
