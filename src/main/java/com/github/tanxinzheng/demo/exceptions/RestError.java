package com.github.tanxinzheng.demo.exceptions;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Jeng on 15/11/29.
 */
public class RestError {

    private Date timestamp;
    private String message;
    private int status;
    private String error;
    private List<FieldError> errors;
    private String path;
    private String exception;

    public RestError() {
    }

    public RestError(Exception ex, HttpServletRequest request) {
        this.timestamp = new Date();
        this.path = request.getRequestURI();
        this.message = ex.getMessage();
        this.error = ex.getMessage();
        this.exception = ex.getClass().getName();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }
}
