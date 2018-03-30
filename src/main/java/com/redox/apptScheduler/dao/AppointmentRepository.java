package com.redox.apptScheduler.dao;

import com.redox.apptScheduler.entity.Appointment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;


/**
 * Appointment Repository
 */
public interface AppointmentRepository extends CrudRepository<Appointment, Long> , JpaSpecificationExecutor {
}
