package com.innovation.wxprogram.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.innovation.wxprogram.dto.WXRunDTO;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WxUtilsTest extends TestCase {

    public void testDecryptWeChatRunInfo() {
//        String s = "{\"session_key\":\"EKlm8N3wZYkBNc+DsFYvDw==\",\"encrypted_data\":\"XqiUFavoe2cX9QpjX/b3LplVFKiy9cn3hBavhpsgikG0WwFmzSqnWAimibmYILH8LGWXKBYijJXy7ZhxR4mNOyS0FAeohwMw1PXhX1FaB1N6F/84A7i5IptUTpAUbzxHnqhs0TGGXpuOkfuF85ACEkJoMi2CM6qkccSZh+4y1soEMvX/fNVf+xjdUaNlAVSOE/sBVXZYursbOjv0uyLBGQgmUuizPiL07ioW0KbB9+MFEwZ94xRdgU9Z4xQ4xSUBiTl0QmrBzT8oV6yC//fR/1YSEwOzljKF/t9YhsDcuY+PZ50GMvQieAX8vmBJS20+2uZXMlnsDQYf24+IQYKHb5UYjztmbEdKZctfMC0P832Zh//TN9xoc3uHdQt1GQgGC2BRjGadUUcgfcd1BZDnStOwYfcSoFzJr0Owvisk6It2TL7Zv3gSlNIDB9DBeEbe20hhqpNqSX0lHQcdVNTI7A==\",\"iv\":\"KOz88Y6I4G01V4Ds/4JxhQ==\"}\n";
//        Map map = (Map)JSON.parse(s);
//        String sessionKey = (String) map.get("session_key");
//        String encryptedData = (String) map.get("encrypted_data");
//        String iv = (String) map.get("iv");
//        System.out.println(WxUtils.decryptWeChatRunInfo(sessionKey,encryptedData,iv));

        String s = "{\"code\":200,\"data\":{\"stepInfoList\":[{\"step\":0,\"timestamp\":1651766400},{\"step\":0,\"timestamp\":1651852800},{\"step\":0,\"timestamp\":1651939200},{\"step\":0,\"timestamp\":1652025600},{\"step\":0,\"timestamp\":1652112000},{\"step\":0,\"timestamp\":1652198400},{\"step\":0,\"timestamp\":1652284800},{\"step\":0,\"timestamp\":1652371200},{\"step\":0,\"timestamp\":1652457600},{\"step\":0,\"timestamp\":1652544000},{\"step\":0,\"timestamp\":1652630400},{\"step\":0,\"timestamp\":1652716800},{\"step\":0,\"timestamp\":1652803200},{\"step\":0,\"timestamp\":1652889600},{\"step\":0,\"timestamp\":1652976000},{\"step\":0,\"timestamp\":1653062400},{\"step\":0,\"timestamp\":1653148800},{\"step\":0,\"timestamp\":1653235200},{\"step\":0,\"timestamp\":1653321600},{\"step\":0,\"timestamp\":1653408000},{\"step\":0,\"timestamp\":1653494400},{\"step\":0,\"timestamp\":1653580800},{\"step\":0,\"timestamp\":1653667200},{\"step\":0,\"timestamp\":1653753600},{\"step\":0,\"timestamp\":1653840000},{\"step\":0,\"timestamp\":1653926400},{\"step\":0,\"timestamp\":1654012800},{\"step\":0,\"timestamp\":1654099200},{\"step\":2732,\"timestamp\":1654185600},{\"step\":891,\"timestamp\":1654272000},{\"step\":41,\"timestamp\":1654358400}],\"watermark\":{\"appid\":\"wx1f435ebeb32d23df\",\"timestamp\":1654419042}},\"msg\":\"success\"}";

        Map<String,String> map = (Map) JSON.parse(s);

        System.out.println(map.get("data"));



//        WXRunDTO wxRunDTO = new WXRunDTO();
//        wxRunDTO.setCode(map.get("code"));
//        wxRunDTO.setMsg(map.get("msg"));

    }
}