package com.redox.apptScheduler.controller;

import com.redox.apptScheduler.dto.*;
import com.redox.apptScheduler.entity.*;
import com.redox.apptScheduler.service.ApptSchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ApptSchedulerController implements a REST API to create, update and track
 * patient appointments with their providers.
 */
@RequestMapping("/appointments")
@RestController
public class ApptSchedulerController {

    private static final Logger logger = LoggerFactory.getLogger(ApptSchedulerController.class);

    @Autowired
    private ApptSchedulerService apptSchedulerService;

    /**
     * Create Appointment
     * @param apptRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value= "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces={"application/vnd.appointments.api.v1+json"})
    public @ResponseBody
    ResponseEntity<Object> createAppointment(@Valid @RequestBody ApptRequest apptRequest)throws Exception {
        ApiResponse apiResponse = null;
        try {
            //check if there is an existing appointment for the specified patient, provider and time
            List<Appointment> appointments = apptSchedulerService.findAppointmentsByCriteria(apptRequest.getPatientId(), apptRequest.getProviderId(),
                    apptRequest.getStartDateTime(), apptRequest.getStartDateTime());
            if (!appointments.isEmpty()) {
                //appointment already exist for the specified patient, provider and time
                apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Appointment already exists");
            } else {
                //create new appointment
                Appointment appointment = new Appointment();
                apiResponse = saveAppointmentWithRequestValues(appointment, apptRequest, true);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), apiResponse.getStatus());
    }

    /**
     * Update Appointment
     * @param apptRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value= "/update",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces={"application/vnd.appointments.api.v1+json"})
    public @ResponseBody
    ResponseEntity<Object> updateAppointment(@Valid @RequestBody ApptRequest apptRequest)throws Exception {
        ApiResponse apiResponse = null;
        try {
            if(apptRequest.getId() != null) {
                Appointment appointment = apptSchedulerService.findAppointmentById(apptRequest.getId());
                if(appointment != null) {
                    //update appointment
                    apiResponse = saveAppointmentWithRequestValues(appointment, apptRequest, false);
                } else {
                    //unable to find specified appointment
                    apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Unable to find the appointment provided");
                }
            } else {
                //send error message
                apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please provide the unique identifier of the appointment to update");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), apiResponse.getStatus());
    }

    /**
     * Search Appointments
     * @param apptSearchCriteria
     * @return
     * @throws Exception
     */
    @RequestMapping(value= "/search",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces={"application/vnd.appointments.api.v1+json"})
    public @ResponseBody
    ResponseEntity<Object> searchAppointments(@Valid @RequestBody ApptSearchCriteria apptSearchCriteria)throws Exception {
        ResponseEntity responseEntity = null;
        try {
            List<Appointment> appointments = apptSchedulerService.findAppointmentsByCriteria(null, null, apptSearchCriteria.getFromDate(), apptSearchCriteria.getToDate());
            responseEntity = new ResponseEntity<Object>(appointments, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
                    new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * Listen to Appointment changes
     * @param apptEvent
     * @return
     * @throws Exception
     */
    @RequestMapping(value= "/eventListener",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces={"application/vnd.appointments.api.v1+json"})
    public @ResponseBody
    ResponseEntity<Object> eventListener(@Valid @RequestBody ApptEvent apptEvent)throws Exception {
        ApiResponse apiResponse = null;
        try {
            logger.info("Received appointment change notification. Appointment "
                    + apptEvent.getAppointmentId() + " is " + apptEvent.getEvent());
            apiResponse = new ApiResponse(HttpStatus.OK, "Received appointment change notification");
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), apiResponse.getStatus());
    }

    /**
     * Helper methods
     */

    /**
     * Save appointment with request values
     * @param appointment
     * @param apptRequest
     * @param isCreate
     * @return
     */
    private ApiResponse saveAppointmentWithRequestValues(Appointment appointment, ApptRequest apptRequest, Boolean isCreate) {
        ApiResponse apiResponse = null;
        //Load patient, provider and department
        Patient patient = apptSchedulerService.findPatientById(apptRequest.getPatientId());
        Provider provider = apptSchedulerService.findProviderById(apptRequest.getProviderId());
        Department department = apptSchedulerService.findDepartmentById(apptRequest.getDepartmentId());
        //if patient, provider and department are correctly specified
        if (patient != null && provider != null && department != null) {
            //save appointment
            appointment.setStartDateTime(apptRequest.getStartDateTime());
            appointment.setDuration(apptRequest.getDuration());
            appointment.setPatient(patient);
            appointment.setProvider(provider);
            appointment.setDepartment(department);
            Date dateCreatedUpdated = new Date();
            if(isCreate) {
                appointment.setCreatedDate(dateCreatedUpdated);
                appointment.setUpdatedDate(dateCreatedUpdated);
            } else {
                appointment.setUpdatedDate(dateCreatedUpdated);
            }
            appointment = apptSchedulerService.saveAppointment(appointment);
            if(isCreate) {
                apiResponse = new ApiResponse(HttpStatus.CREATED, "Appointment Created");
                notifySubscribers(new ApptEvent(appointment.getId(), Event.CREATED));
            } else {
                apiResponse = new ApiResponse(HttpStatus.OK, "Appointment Updated");
                notifySubscribers(new ApptEvent(appointment.getId(), Event.UPDATED));
            }
        } else {
            //send error message
            apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Unable to find Patient or Provider or Department");
        }
        return apiResponse;
    }

    /**
     * Notify all subscribers of Appointment creates/updates
     * @param apptEvent
     */
    private void notifySubscribers(ApptEvent apptEvent) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            Iterable<Webhook> webhooks = apptSchedulerService.findAllWebhooks();
            for (Webhook webhook : webhooks) {
                HttpEntity<ApptEvent> request = new HttpEntity<>(apptEvent);
                ResponseEntity<ApptEvent> response = restTemplate.exchange(webhook.getUrl(), HttpMethod.POST, request, ApptEvent.class);
                logger.info(webhook.getCompanyName() + "(" + webhook.getUrl()
                        + ") is notified. Response Status: " + response.getStatusCode().toString());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
