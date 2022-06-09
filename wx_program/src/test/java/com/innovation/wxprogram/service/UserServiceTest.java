package com.innovation.wxprogram.service;

import com.alibaba.fastjson.JSON;
import com.innovation.wxprogram.entity.User;
import com.innovation.wxprogram.utils.RequestUtils;
import junit.framework.TestCase;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class UserServiceTest extends TestCase {

    UserService userService = new UserService();

    public void testSelectByOpenid() {
        User user = userService.selectByOpenid("oq1TZz6dFYf8HzwVqeYhh1KTij5IY");
        System.out.println(user.getNickName());
    }

    public void testInsert() {
        User user = new User();
        user.setOpenid("1232131");
        user.setNickName("小夏");
        user.setPhoneNumber("15142662711");
        user.setRealName("夏新宇");
        user.setSumDistance(10000);
        userService.insert(user);
    }

    public void testGet(){
        String code = "003L83ll2Dk7i9407tll22yLhX3L83l1";
        try{
            String resp = RequestUtils.getOpenId(code);

            Map json = (Map) JSON.parse(resp);
            System.out.println(json);
            String openid = (String)json.get("openid");
            System.out.println(openid);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void testSelectBySessionKet(){
        User user = userService.selectBySessionId("kwDxtgLQFzehkgKiyuL+XQ==");
        System.out.println(user);
    }
}