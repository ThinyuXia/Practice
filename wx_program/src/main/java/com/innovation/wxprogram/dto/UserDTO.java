package com.innovation.wxprogram.dto;

public class UserDTO {
    private String nickName;
    private String realName;
    private String phoneNumber;

    public UserDTO(String nickName, String realName, String phoneNumber) {
        this.nickName = nickName;
        this.realName = realName;
        this.phoneNumber = phoneNumber;
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

}
