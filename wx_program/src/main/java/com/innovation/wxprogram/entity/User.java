package com.innovation.wxprogram.entity;

public class User {

    private String openid;
    private String nickName;
    private String realName;
    private String phoneNumber;
    private Integer sumDistance;
    private String sessionKey;

    public User() {
    }

    public User(String openid, String nickName, String realName, String phoneNumber, Integer sumDistance, String sessionKey) {
        this.openid = openid;
        this.nickName = nickName;
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.sumDistance = sumDistance;
        this.sessionKey = sessionKey;
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

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
