package org.feather.bz.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.utils
 * @className: MD5Utils
 * @author: feather
 * @description:
 * @since: 2025-04-27 15:21
 * @version: 1.0
 */
public class MD5Utils {
    /**
     *
     * @Title: MD5Utils.java
     * @Description: 对字符串进行md5加密
     */
    public static String getMD5Str(String strValue) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest(strValue.getBytes()));
    }
}
