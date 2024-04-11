package org.oj.constant;

/**
 * 接口返回状态码
 *
 * @author DH
 * @create 2020/12/1/
 */
public class ResCode {

    /**
     * 成功
     * 2----，添加 201--，删除 202--，更新 203--，获取 204--，排序 205--，导入207--，导出208--
     */
    public static final int SUCCESS = 20000;

    /**
     * 问题 已知的
     * 4----，账户 401--，未找到 404--，删除 405---，冲突 409--
     */
    public static final int BAD = 40000;
    public static final int UNAUTHORIZED = 40100;
    public static final int ACCESS_DENIED = 40103;
    public static final int NOT_FOUND = 40400;

    /**
     * 异常 未知的、catch 的
     * 5----，添加 501--，删除 502--，更新 503--，获取 504--，排序 505--，登录 506--，导入507--，导出508--
     */
    public static final int FAIL = 50000;
}

// public enum ResCode {
//     /**
//      * 成功
//      */
//     SUCCESS(20000),
//
//     /**
//      * 已知错误
//      */
//     BAD(40000),
//     /**
//      * 未登录
//      */
//     UNAUTHORIZED(40100),
//     /**
//      * 无操作权限
//      */
//     ACCESS_DENIED(40103),
//     /**
//      * 资源不存在
//      */
//     NOT_FOUND(40400),
//
//     /**
//      * 未知异常
//      */
//     FAIL(50000)
// }
