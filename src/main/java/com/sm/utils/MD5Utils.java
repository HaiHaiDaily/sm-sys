package com.sm.utils;

import org.springframework.util.DigestUtils;

/**
 * MD5加密
 */
public class MD5Utils {
    public static String getMD5(String str){
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
