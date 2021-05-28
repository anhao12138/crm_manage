package com.w.crmsystem.util;

import java.security.MessageDigest;

/**
 * @ClassName:
*  @Description:
 * @Author: yun
 * @Date 2021/4/26 15:47
 * @Version 1.0
 */
public class PasswordUtil {
    //盐，用于混交md5
    private static final String slat = "&%5123***&&%%$$#@";

    public static String encodePwd(String dataStr) {
        try {
            dataStr = dataStr + slat;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
