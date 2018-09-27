package cn.echocow.drcom.utils;

import java.security.MessageDigest;

/**
 * Md5Util
 *      md5 加密类
 * @author echo
 * @version 1.0
 * @date 18-9-26 下午3:12
 */
public class Md5Util {
    /**
     * md5 加密
     * @param plainText 加密文本
     * @return 加密后的文本
     */
    private static String encode(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuilder buf = new StringBuilder();
            for (byte aB : b) {
                i = aB;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 登录密码加密
     * @param password 密码
     * @return 加密后的密码
     */
    public static String getPassword(String password) {
        String pid = "2";
        String calg = "12345678";
        return encode(pid + password + calg) + calg + pid;
    }

}
