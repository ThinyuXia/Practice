package com.xiaxinyu.OA.service.exception;

/**
 * 业务逻辑异常
 */
public class BusinessException extends RuntimeException{
    private String code; //异常编码
    private String msg; //异常描述信息
    public BusinessException(String code,String msg){
        super(code + ":" + msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
