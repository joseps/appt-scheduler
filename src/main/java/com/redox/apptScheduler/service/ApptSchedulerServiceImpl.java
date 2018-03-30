package com.redox.apptScheduler.service;

import com.redox.apptScheduler.dao.*;
import com.redox.apptScheduler.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the service interface for appointment scheduling and tracking
 */
@Service
public class ApptSchedulerServiceImpl implements ApptSchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(ApptSchedulerServiceImpl.class);

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private WebhookRepository webhookRepository;

    public Appointment findAppointmentById(Long id){
        return appointmentRepository.findOne(id);
    }

    public List<Appointment> findAppointmentsByCriteria(Long patientId, Long providerId, Date fromDateTime, Date toDateTime){
        List<Appointment> appointments = appointmentRepository.findAll(new Specification<Appointment>() {
            @Override
            public Predicate toPredicate(Root<Appointment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicates = new ArrayList<>();
                if(patientId != null) {
                    predicates.add(criteriaBuilder.equal(root.get("patient"), patientId));
                }
                if(providerId != null) {
                    predicates.add(criteriaBuilder.equal(root.get("provider"), providerId));
                }
                if(fromDateTime != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDateTime"), fromDateTime));
                }
                if(toDateTime != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("startDateTime"), toDateTime));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        });
        return appointments;
    }

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Patient findPatientById(Long id) {
        return patientRepository.findOne(id);
    }

    public Provider findProviderById(Long id) {
        return providerRepository.findOne(id);
    }

    public Department findDepartmentById(Long id) {
        return departmentRepository.findOne(id);
    }

    public Iterable<Webhook> findAllWebhooks() {
        return webhookRepository.findAll();
    }

}
