package com.exodia.webservice.model.common;

/**
 * Created by manlm1 on 9/12/2015.
 */
public class GenericResponse<E> {

    private E data;
    private String message;
    private String statusCode;

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
