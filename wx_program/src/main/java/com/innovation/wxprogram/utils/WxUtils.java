package com.innovation.wxprogram.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;

public class WxUtils {

    // 步数转卡路里
    public static float getDistanceByStep(long steps) {
        return steps * 0.6f / 1000;
    }
    /**
     * 微信解密运动步数
     *
     * @param sessionKey
     * @param encryptedData
     * @param iv
     * @return
     */
    public static String decryptWeChatRunInfo(String sessionKey, String encryptedData, String iv) {
        String result = null;
        byte[] encrypData = Base64.decodeBase64(encryptedData);
        byte[] ivData = Base64.decodeBase64(iv);
        byte[] sessionKeyB = Base64.decodeBase64(sessionKey);
        try {
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(sessionKeyB, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] doFinal = cipher.doFinal(encrypData);
            result = new String(doFinal);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}