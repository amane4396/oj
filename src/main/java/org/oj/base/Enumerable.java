package org.oj.base;

/**
 * 枚举类型基础接口
 *
 * @author DH
 * @create 2020-12-11
 */
public interface Enumerable {
    /**
     * 获取枚举值
     *
     * @return int
     */
    int getKey();

    /**
     * 获取枚举名称
     *
     * @return string
     */
    String getName();

}
