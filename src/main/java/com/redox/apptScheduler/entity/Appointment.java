package com.redox.apptScheduler.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Appointment Entity
 */
@Entity
public class Appointment extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startDateTime;

    private Integer duration;

    @OneToOne(optional=false)
    @JoinColumn(name="patient_id", nullable=false, updatable=true)
    private Patient patient;

    @OneToOne(optional=false)
    @JoinColumn(name="provider_id", nullable=false, updatable=true)
    private Provider provider;

    @OneToOne(optional=false)
    @JoinColumn(name="department_id", nullable=false, updatable=true)
    private Department department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
