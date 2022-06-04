package com.innovation.wxprogram.utils;

import org.springframework.web.client.RestTemplate;

public class RequestUtils {
    private static RestTemplate restTemplate = new RestTemplate();
    private static String appid = "wx1f435ebeb32d23df";
    private static String appSecret = "55d87f7ee640c09368a53071d38a1934";

    public static String getOpenId(String code){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+ appid +"&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        return restTemplate.getForObject(url,String.class);
    }


}
