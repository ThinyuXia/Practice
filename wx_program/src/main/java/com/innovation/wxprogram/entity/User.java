package com.innovation.wxprogram.entity;

public class User {

    private String openid;
    private String nickName;
    private String realName;
    private String phoneNumber;
    private Integer sumDistance;
    private String sessionId;

    public User() {
    }

    public User(String openid, String nickName, String realName, String phoneNumber, Integer sumDistance, String sessionId) {
        this.openid = openid;
        this.nickName = nickName;
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.sumDistance = sumDistance;
        this.sessionId = sessionId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getSumDistance() {
        return sumDistance;
    }

    public void setSumDistance(Integer sumDistance) {
        this.sumDistance = sumDistance;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
