package com.w.crmsystem.util;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/9 15:40
 * @Version 1.0
 */
public class StringUtil {
    public static boolean isEmptiy(String str){
        return str.trim()==null||str.trim()==""||"".equals(str.trim())?false:true;
    }

    public static String delSpace(String str){
        return str.trim();
    }
}
