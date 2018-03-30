package com.redox.apptScheduler.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ApptEvent is used for sending notifications to subscribers of appointment
 * change events
 */
public class ApptEvent implements Serializable{

    @NotNull
    private Long appointmentId;

    @NotNull
    private Event event;

    public ApptEvent(){};

    public ApptEvent(Long appointmentId, Event event) {
        this.appointmentId = appointmentId;
        this.event = event;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
