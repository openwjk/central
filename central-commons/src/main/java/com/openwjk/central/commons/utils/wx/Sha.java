package com.openwjk.central.commons.utils.wx;

import java.security.MessageDigest;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/7 10:52
 */
public class Sha {
    private static final int HEX = 16;
    /**
     * SHA: 0xFF
     */
    private static final int SHA_FF = 0xFF;
    /**
     * SHA: 0x100
     */
    private static final int SHA_100 = 0x100;

    /**
     * SHA算法实现
     *
     * @param msg 明文
     * @return 密文
     */
    public static String encryptSHA(final String msg) {

        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(msg.getBytes());
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & SHA_FF) + SHA_100, HEX).substring(1));
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
