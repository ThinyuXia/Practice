package com.xiaxinyu.mall.exception;

/**
 * 统一异常
 */
public class ExceptionUnify extends  Exception{
    private final Integer code;
    private final String msg;

    public ExceptionUnify(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ExceptionUnify(ExceptionEnum exceptionEnum){
        this(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
