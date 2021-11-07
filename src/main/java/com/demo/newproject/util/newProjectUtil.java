package com.demo.newproject.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.MessageDigest;


public class newProjectUtil {


    private static final Logger logger = LoggerFactory.getLogger(newProjectUtil.class);

    private static final String[] suffixNameTable = {"jpg", "bmp", "jpeg", "webbp", "pcx", "tif", "gif",
            "tga", "exif", "fpx", "svg", "psd", "cdr", "pcd", "dxf", "ufo", "eps", "ai",
    "png", "hdri", "raw", "wmf", "flic", "emf", "ico"};

    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};

    public static String MD5(String key) {
        try {
            byte[] btInput = key.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            logger.error("生成MD5失败", e);
            return null;
        }
    }

    public static boolean isPic(String suffixName) {
        if(suffixName == null || suffixName.isBlank()) {
            return false;
        }
        String target  = suffixName.toLowerCase().split("\\.")[1];
        for(String i : suffixNameTable) {
            if(target.equals(i)) {
                return true;
            }
        }
        return false;
    }

}
