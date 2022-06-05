package com.innovation.wxprogram.dto;

import java.util.ArrayList;
import java.util.HashMap;

public class WXRunDTO {
    private String code;
    private String msg;
    private HashMap<String, ArrayList<HashMap<String,String>>> stepInfoList;

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

    public HashMap<String, ArrayList<HashMap<String, String>>> getStepInfoList() {
        return stepInfoList;
    }

    public void setStepInfoList(HashMap<String, ArrayList<HashMap<String, String>>> stepInfoList) {
        this.stepInfoList = stepInfoList;
    }
}
