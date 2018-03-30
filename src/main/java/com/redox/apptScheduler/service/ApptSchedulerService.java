package com.redox.apptScheduler.service;

import com.redox.apptScheduler.entity.*;

import java.util.Date;
import java.util.List;

/**
 * Primary service interface for appointment scheduling and tracking
 * used by the REST API
 */
public interface ApptSchedulerService {

    public Appointment findAppointmentById(Long id);
    public List<Appointment> findAppointmentsByCriteria(Long patientId, Long providerId, Date fromDateTime, Date toDateTime);
    public Appointment saveAppointment(Appointment appointment);

    public Patient findPatientById(Long id);

    public Provider findProviderById(Long id);

    public Department findDepartmentById(Long id);

    public Iterable<Webhook> findAllWebhooks();

}
