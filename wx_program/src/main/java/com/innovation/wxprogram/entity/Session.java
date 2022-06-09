package com.innovation.wxprogram.entity;

public class Session {
    private String sessionId;
    private String sessionKey;

    public Session() {
    }

    public Session(String sessionId, String sessionKey) {
        this.sessionId = sessionId;
        this.sessionKey = sessionKey;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
