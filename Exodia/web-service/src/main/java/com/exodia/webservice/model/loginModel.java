package com.exodia.webservice.model;

/**
 * Created by manlm1 on 9/12/2015.
 */
public class LoginModel {

    private int playerId;
    private String sessionId;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
