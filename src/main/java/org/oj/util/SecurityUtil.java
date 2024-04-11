package org.oj.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * 安全相关工具
 *
 * @author DH
 * @create 2021-10-25
 */
@Component
public class SecurityUtil {

    public final static String SALT = "wxKYXuTPST5SG0jMQzVPsg==";

    public static String encrypt(String type, String s, String salt) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(type);
            String text = "";
            if (s != null) {
                if (StringUtils.isEmpty(salt)) {
                    text = s;
                } else {
                    text = s + salt;
                }
            }
            result = byteUtil.bytesToHexString(md.digest(text.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception ignored) {
        }
        return result;
    }

}
