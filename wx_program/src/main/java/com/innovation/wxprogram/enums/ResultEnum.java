package com.innovation.wxprogram.enums;


public enum ResultEnum {
    PARAM_ERROR(1,"参数不正确"),
    LOGIN_ERROR(2,"接口调用错误，当前用户不是新用户，session_key已更新"),
    CODE_ERROR(3,"登陆凭证码code已过期")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
