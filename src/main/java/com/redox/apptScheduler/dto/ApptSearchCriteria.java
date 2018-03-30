package com.redox.apptScheduler.dto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * ApptSearchCriteria is the request object expected by the REST API for
 * searching existing appointments.
 */
public class ApptSearchCriteria implements Serializable{
    @NotNull
    private Date fromDate;

    @NotNull
    private Date toDate;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
