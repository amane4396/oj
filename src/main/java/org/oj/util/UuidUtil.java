package org.oj.util;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author DH
 * @create 2021-10-14
 */
public class UuidUtil {

    /**
     * 生成32字符长度的随机UUID
     */
    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
