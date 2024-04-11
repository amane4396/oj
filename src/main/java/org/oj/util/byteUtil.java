package org.oj.util;

/**
 * 字节相关工具类
 *
 * @author DH
 * @create 2021-10-25
 */

public class byteUtil {

    final protected static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    /**
     * 转换字节数组为16进制字串
     *
     * @param bytes 字节数组
     * @return 16进制字串
     */
    public static String bytesToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * 字节转换为16进制字符串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteToHexString(byte b) {
        int v = b & 0xFF;
        return HEX_ARRAY[v >>> 4] + String.valueOf(HEX_ARRAY[v & 0x0F]);
    }
}
