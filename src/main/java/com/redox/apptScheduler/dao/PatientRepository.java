package com.redox.apptScheduler.dao;

import com.redox.apptScheduler.entity.Patient;
import org.springframework.data.repository.CrudRepository;

/**
 * Patient Repository
 */
public interface PatientRepository extends CrudRepository<Patient, Long> {
}

