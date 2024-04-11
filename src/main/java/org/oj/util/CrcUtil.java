package org.oj.util;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * CRC16 ANSI
 *
 * @author DH
 * @create 2021-10-22
 */
public class CrcUtil {
    public static String ansi16(String s) {
        byte[] data = s.getBytes(StandardCharsets.UTF_8);
        int crc = 0xFFFF;
        int len = data.length;
        for (byte b : data) {
            crc = (int) ((crc >> 8) ^ b);
            for (int j = 0; j < 8; j++) {
                crc = (crc & 1) == 1 ? (int) ((crc >> 1) ^ 0xA001) : (int) (crc >> 1);
            }
        }
        return StringUtils.leftPad(Integer.toHexString(crc).toUpperCase(), 4, '0');
    }
}
