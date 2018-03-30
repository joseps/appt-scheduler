package com.redox.apptScheduler.dto;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

/**
 * ApiResponse is used for sending a response to a REST request
 */
public class ApiResponse implements Serializable{

    private HttpStatus status;
    private String message;

    public ApiResponse(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public ApiResponse(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}