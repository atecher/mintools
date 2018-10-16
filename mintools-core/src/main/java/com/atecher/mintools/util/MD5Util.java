package com.atecher.mintools.util;


import java.security.MessageDigest;

/**
 * 描述：MD5工具类
 *
 * @作者 mark.han
 * @邮箱 hongwei.han@qq.com
 * @日期 2014-7-29
 * @版本 v1.0
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class MD5Util {
    private static String md5(String s) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes(Constants.DEFAULT_CHARSET);
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String md5(String sourceStr, int len) {
        String result = md5(sourceStr);
        if (len == 16) {
            assert result != null;
            return result.substring(8, 24);
        } else if (len == 32) {
            return result;
        } else {
            throw new RuntimeException("支持16位和32位MD5加密");
        }
    }
}
