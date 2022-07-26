package com.xiaxinyu.mall.common;

import com.xiaxinyu.mall.exception.ExceptionEnum;

/**
 * 通用返回对象
 */
public class ApiRestResponse<T> {
    private Integer statue;
    private String msg;
    private T data;

    private static final int OK_CODE = 10000;
    private static final String OK_SUCCESS = "success";

    public ApiRestResponse() {
        this(OK_CODE,OK_SUCCESS);
    }

    public ApiRestResponse(Integer statue, String msg) {
        this.statue = statue;
        this.msg = msg;
    }

    public ApiRestResponse(Integer statue, String msg, T data) {
        this.statue = statue;
        this.msg = msg;
        this.data = data;
    }

    //由于静态方法并不属于类的一部分，所以使用<T>将静态方法声明为范型方法
    public static <T> ApiRestResponse<T> success(){
        return new ApiRestResponse<>();
    }

    public static <T> ApiRestResponse<T> success(T result){
        ApiRestResponse<T> response = new ApiRestResponse<>();
        response.setData(result);
        return response;
    }

    public static <T> ApiRestResponse<T> error(Integer code,String msg){
        return new ApiRestResponse<>(code,msg);
    }

    public static <T> ApiRestResponse<T> error(ExceptionEnum ex){
        return new ApiRestResponse<>(ex.getCode(), ex.getMsg());
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static int getOkCode() {
        return OK_CODE;
    }

    public static String getOkSuccess() {
        return OK_SUCCESS;
    }

    @Override
    public String toString() {
        return "ApiRestResponse{" +
                "statue=" + statue +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
