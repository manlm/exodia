package com.exodia.webservice.model;

/**
 * Created by manlm1 on 9/26/2015.
 */
public class ReauthorizeModel {

    private String email;
    private String sessionId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
