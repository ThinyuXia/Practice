package com.innovation.wxprogram.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class PostUtils {
    public static Map<String,String> getBody(HttpServletRequest req) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String encode = "utf-8";
        BufferedReader in = new BufferedReader(new InputStreamReader(
                req.getInputStream(), encode));
        String data = "";
        String line;
        while ((line = in.readLine()) != null) {
            data = data + line;
        }
        in.close();
        return (Map<String,String>)JSON.parse(data);
    }
}
