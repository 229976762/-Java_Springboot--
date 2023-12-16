package com.ghy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author GHY
 * @date 2023/11/21
 */

public class MD5Util {

    public static String md5(String password) {
        try {
            // 获取MD5实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 更新摘要
            md.update(password.getBytes());
            // 获取密文字节数组
            byte[] digest = md.digest();
            // 将字节数组转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
