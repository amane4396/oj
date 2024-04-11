package org.oj.util;


import org.oj.constant.ResCode;
import org.oj.constant.ResMsg;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

/**
 * 接口返回数据封装
 *
 * @author DH
 * @create 2020-12-11
 */
public class JsonRes extends HashMap<String, Object> {

    /////////////////////// 默认的键 ///////////////////////

    public static final String KEY_CODE = "code";
    public static final String KEY_MSG = "message";
    public static final String KEY_DATA = "data";

    /**
     * 全参构造
     *
     * @param code 状态码
     * @param msg 提示信息
     * @param data 数据
     */
    public JsonRes(int code, String msg, Object data) {
        this.put(KEY_CODE, code);
        if (!StringUtils.isEmpty(msg)) {
            this.put(KEY_MSG, msg);
        }
        if (data != null) {
            this.put(KEY_DATA, data);
        }
    }


    // region 成功 success

    /**
     * 成功；
     * 状态码：20000；
     * 提示信息：无；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes success() {
        return new JsonRes(ResCode.SUCCESS, null, null);
    }

    /**
     * 成功；
     * 状态码：20000；
     * 提示信息：msg；
     * 返回数据：无；
     *
     * @param msg 提示信息 提示信息
     * @return JsonRes对象，Json封装
     */
    public static JsonRes success(String msg) {
        return new JsonRes(ResCode.SUCCESS, msg, null);
    }

    /**
     * 成功；
     * 状态码：20000；
     * 提示信息：无；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes success(Object data) {
        return new JsonRes(ResCode.SUCCESS, null, data);
    }

    /**
     * 成功；
     * 状态码：20000；
     * 提示信息：msg；
     * 返回数据：data；
     *
     * @param msg 提示信息  提示信息
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes success(String msg, Object data) {
        return new JsonRes(ResCode.SUCCESS, msg, data);
    }

    /**
     * 成功，添加数据；
     * 状态码：20000；
     * 提示信息：数据添加成功；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes createSuccess() {
        return new JsonRes(ResCode.SUCCESS, ResMsg.CREATE_SUCCESS, null);
    }

    /**
     * 成功，添加数据；
     * 状态码：20000；
     * 提示信息：数据添加成功；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes createSuccess(Object data) {
        return new JsonRes(ResCode.SUCCESS, ResMsg.CREATE_SUCCESS, data);
    }

    /**
     * 成功，删除数据；
     * 状态码：20000；
     * 提示信息：数据删除成功；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes deleteSuccess() {
        return new JsonRes(ResCode.SUCCESS, ResMsg.DELETE_SUCCESS, null);
    }

    /**
     * 成功，删除数据；
     * 状态码：20000；
     * 提示信息：数据删除成功；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes deleteSuccess(Object data) {
        return new JsonRes(ResCode.SUCCESS, ResMsg.DELETE_SUCCESS, data);
    }

    /**
     * 成功，更新数据；
     * 状态码：20000；
     * 提示信息：数据更新成功；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes updateSuccess() {
        return new JsonRes(ResCode.SUCCESS, ResMsg.UPDATE_SUCCESS, null);
    }

    /**
     * 成功，更新数据；
     * 状态码：20000；
     * 提示信息：数据更新成功；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes updateSuccess(Object data) {
        return new JsonRes(ResCode.SUCCESS, ResMsg.UPDATE_SUCCESS, data);
    }

    /**
     * 成功，获取数据；
     * 状态码：20000；
     * 提示信息：数据获取成功；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes getDataSuccess() {
        return new JsonRes(ResCode.SUCCESS, ResMsg.GET_DATA_SUCCESS, null);
    }

    /**
     * 成功，获取数据；
     * 状态码：20000；
     * 提示信息：数据获取成功；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes getDataSuccess(Object data) {
        return new JsonRes(ResCode.SUCCESS, ResMsg.GET_DATA_SUCCESS, data);
    }

    /**
     * 成功，调整排序；
     * 状态码：20000；
     * 提示信息：调整排序成功；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes rankSuccess() {
        return new JsonRes(ResCode.SUCCESS, ResMsg.RANK_SUCCESS, null);
    }

    /**
     * 成功，调整排序；
     * 状态码：20000；
     * 提示信息：调整排序成功；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes rankSuccess(Object data) {
        return new JsonRes(ResCode.SUCCESS, ResMsg.RANK_SUCCESS, data);
    }

    /**
     * 成功，导入数据；
     * 状态码：20000；
     * 提示信息：数据导入成功；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes importSuccess() {
        return new JsonRes(ResCode.SUCCESS, ResMsg.IMPORT_SUCCESS, null);
    }

    /**
     * 成功，导入数据；
     * 状态码：20000；
     * 提示信息：数据导入成功；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes importSuccess(Object data) {
        return new JsonRes(ResCode.SUCCESS, ResMsg.IMPORT_SUCCESS, data);
    }

    /**
     * 成功，导出数据；
     * 状态码：20000；
     * 提示信息：数据导出成功；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes exportSuccess() {
        return new JsonRes(ResCode.SUCCESS, ResMsg.EXPORT_SUCCESS, null);
    }

    /**
     * 成功，导出数据；
     * 状态码：20000；
     * 提示信息：数据导出成功；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes exportSuccess(Object data) {
        return new JsonRes(ResCode.SUCCESS, ResMsg.EXPORT_SUCCESS, data);
    }
    // endregion

    // region 已知问题 bad

    /**
     * 已知的错误；
     * 状态码：40000；
     * 提示信息：操作未能执行；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes bad() {
        return new JsonRes(ResCode.BAD, ResMsg.BAD, null);
    }

    /**
     * 已知的错误；
     * 状态码：40000；
     * 提示信息：msg；
     * 返回数据：无；
     *
     * @param msg 提示信息 提示信息
     * @return JsonRes对象，Json封装
     */
    public static JsonRes bad(String msg) {
        return new JsonRes(ResCode.BAD, msg, null);
    }

    /**
     * 已知的错误；
     * 状态码：40000；
     * 提示信息：无；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes bad(Object data) {
        return new JsonRes(ResCode.BAD, null, data);
    }

    /**
     * 已知的错误；
     * 状态码：40000；
     * 提示信息：msg；
     * 返回数据：data；
     *
     * @param msg 提示信息  提示信息
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes bad(String msg, Object data) {
        return new JsonRes(ResCode.BAD, msg, data);
    }

    /**
     * 已知的错误，未携带Token、用户不存在、Token过期；
     * 前端跳转登录页
     * 状态码：40100；
     * 提示信息：无；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes unauthorized() {
        return new JsonRes(ResCode.UNAUTHORIZED, null, null);
    }

    /**
     * 已知的错误，无操作权限；
     * 状态码：40103；
     * 提示信息：无操作权限；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes accessDenied() {
        return new JsonRes(ResCode.ACCESS_DENIED, ResMsg.ACCESS_DENIED, null);
    }

    /**
     * 已知的错误，数据不存在；
     * 状态码：40400；
     * 提示信息：数据不存在；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes notFound() {
        return new JsonRes(ResCode.NOT_FOUND, ResMsg.NOT_FOUND, null);
    }

    /**
     * 已知的错误，调整排序；
     * 状态码：40000；
     * 提示信息：已到最前位置；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes rankIsTop() {
        return new JsonRes(ResCode.BAD, ResMsg.RANK_UP_DATA_IS_TOP, null);
    }

    /**
     * 已知的错误，删除数据；
     * 状态码：40000；
     * 提示信息：msg；
     * 返回数据：无；
     *
     * @param msg 提示信息 提示信息，可从：
     *            ResMsg.ALL_DELETE_DATA_NOT_FOUND
     *            ResMsg.PARTIAL_DELETE_DATA_NOT_FOUND
     *            ResMsg.ALL_DELETE_DATA_ASSOCIATED
     *            ResMsg.PARTIAL_DELETE_DATA_ASSOCIATED
     *            ResMsg.DELETE_DATA_NOT_FOUND_OR_ASSOCIATED
     *            获取，或自定义，注意获取的提示信息中，有些需要补充信息
     * @return JsonRes对象，Json封装
     */
    public static JsonRes deleteIncomplete(String msg) {
        return new JsonRes(ResCode.BAD, msg, null);
    }

    /**
     * 已知的错误，删除数据；
     * 状态码：40000；
     * 提示信息：msg；
     * 返回数据：data；
     *
     * @param msg 提示信息  提示信息，可从：
     *             ResMsg.ALL_DELETE_DATA_NOT_FOUND
     *             ResMsg.PARTIAL_DELETE_DATA_NOT_FOUND
     *             ResMsg.ALL_DELETE_DATA_ASSOCIATED
     *             ResMsg.PARTIAL_DELETE_DATA_ASSOCIATED
     *             ResMsg.DELETE_DATA_NOT_FOUND_OR_ASSOCIATED
     *             获取，或自定义，注意获取的提示信息中，有些需要补充信息
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes deleteIncomplete(String msg, Object data) {
        return new JsonRes(ResCode.BAD, msg, data);
    }

    /**
     * 已知的错误，数据冲突；
     * 状态码：40000；
     * 提示信息：msg；
     * 返回数据：无；
     *
     * @param msg 提示信息 提示信息，可从：
     *            ResMsg.CREATE_CONFLICT
     *            ResMsg.UPDATE_CONFLICT
     *            获取前半段提示信息，自定义后半段详细信息
     * @return JsonRes对象，Json封装
     */
    public static JsonRes createConflict(String msg) {
        return new JsonRes(ResCode.BAD, msg, null);
    }

    /**
     * 已知的错误，数据冲突；
     * 状态码：40000；
     * 提示信息：msg；
     * 返回数据：data；
     *
     * @param msg 提示信息 提示信息，可从：
     *            ResMsg.CREATE_CONFLICT
     *            ResMsg.UPDATE_CONFLICT
     *            获取前半段提示信息，自定义后半段详细信息
     * @return JsonRes对象，Json封装
     */
    public static JsonRes updateConflict(String msg) {
        return new JsonRes(ResCode.BAD, msg, null);
    }
// endregion

    // region 未知错误 fail

    /**
     * 未知的错误，异常；
     * 状态码：50000；
     * 提示信息：请求发生错误；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes fail() {
        return new JsonRes(ResCode.FAIL, ResMsg.FAIL, null);
    }

    /**
     * 未知的错误，异常；
     * 状态码：50000；
     * 提示信息：msg；
     * 返回数据：无；
     *
     * @param msg 提示信息 提示信息
     * @return JsonRes对象，Json封装
     */
    public static JsonRes fail(String msg) {
        return new JsonRes(ResCode.FAIL, msg, null);
    }

    /**
     * 未知的错误，异常；
     * 状态码：50000；
     * 提示信息：请求发生错误；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes fail(Object data) {
        return new JsonRes(ResCode.FAIL, ResMsg.FAIL, data);
    }

    /**
     * 未知的错误，异常；
     * 状态码：50000；
     * 提示信息：msg；
     * 返回数据：data；
     *
     * @param msg 提示信息  提示信息
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes fail(String msg, Object data) {
        return new JsonRes(ResCode.FAIL, msg, data);
    }

    /**
     * 未知的错误，添加数据异常；
     * 状态码：50000；
     * 提示信息：数据添加发生错误；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes createFail() {
        return new JsonRes(ResCode.FAIL, ResMsg.CREATE_FAIL, null);
    }

    /**
     * 未知的错误，添加数据异常；
     * 状态码：50000；
     * 提示信息：数据添加发生错误；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes createFail(Object data) {
        return new JsonRes(ResCode.FAIL, ResMsg.CREATE_FAIL, data);
    }

    /**
     * 未知的错误，删除数据异常；
     * 状态码：50000；
     * 提示信息：数据删除发生错误；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes deleteFail() {
        return new JsonRes(ResCode.FAIL, ResMsg.DELETE_FAIL, null);
    }

    /**
     * 未知的错误，删除数据异常；
     * 状态码：50000；
     * 提示信息：数据删除发生错误；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes deleteFail(Object data) {
        return new JsonRes(ResCode.FAIL, ResMsg.DELETE_FAIL, data);
    }

    /**
     * 未知的错误，更新数据异常；
     * 状态码：50000；
     * 提示信息：数据更新发生错误；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes updateFail() {
        return new JsonRes(ResCode.FAIL, ResMsg.UPDATE_FAIL, null);
    }

    /**
     * 未知的错误，更新数据异常；
     * 状态码：50000；
     * 提示信息：数据更新发生错误；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes updateFail(Object data) {
        return new JsonRes(ResCode.FAIL, ResMsg.UPDATE_FAIL, data);
    }

    /**
     * 未知的错误，数据获取异常；
     * 状态码：50000；
     * 提示信息：数据获取发生错误；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes getDataFail() {
        return new JsonRes(ResCode.FAIL, ResMsg.GET_DATA_FAIL, null);
    }

    /**
     * 未知的错误，数据获取异常；
     * 状态码：50000；
     * 提示信息：数据获取发生错误；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes getDataFail(Object data) {
        return new JsonRes(ResCode.FAIL, ResMsg.GET_DATA_FAIL, data);
    }

    /**
     * 未知的错误，调整排序异常；
     * 状态码：50000；
     * 提示信息：调整排序发生错误；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes rankFail() {
        return new JsonRes(ResCode.FAIL, ResMsg.RANK_FAIL, null);
    }

    /**
     * 未知的错误，调整排序异常；
     * 状态码：50000；
     * 提示信息：调整排序发生错误；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes rankFail(Object data) {
        return new JsonRes(ResCode.FAIL, ResMsg.RANK_FAIL, data);
    }

    /**
     * 未知的错误，导入数据异常；
     * 状态码：50000；
     * 提示信息：导入发生错误；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes importFail() {
        return new JsonRes(ResCode.FAIL, ResMsg.IMPORT_FAIL, null);
    }

    /**
     * 未知的错误，导入数据异常；
     * 状态码：50000；
     * 提示信息：导入发生错误；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes importFail(Object data) {
        return new JsonRes(ResCode.FAIL, ResMsg.IMPORT_FAIL, data);
    }

    /**
     * 未知的错误，导出数据异常；
     * 状态码：50000；
     * 提示信息：导出发生错误；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes exportFail() {
        return new JsonRes(ResCode.FAIL, ResMsg.EXPORT_FAIL, null);
    }

    /**
     * 未知的错误，导出数据异常；
     * 状态码：50000；
     * 提示信息：导出发生错误；
     * 返回数据：data；
     *
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes exportFail(Object data) {
        return new JsonRes(ResCode.FAIL, ResMsg.EXPORT_FAIL, data);
    }

    /**
     * 未知的错误，登录异常；
     * 状态码：50000；
     * 提示信息：登录发生错误；
     * 返回数据：无；
     *
     * @return JsonRes对象，Json封装
     */
    public static JsonRes loginFail() {
        return new JsonRes(ResCode.FAIL, ResMsg.LOGIN_FAIL, null);
    }
    // endregion

    // region 自定义 result

    /**
     * 自定义返回
     *
     * @param code 状态码 状态码
     * @return JsonRes对象，Json封装
     */
    public static JsonRes result(int code) {
        return new JsonRes(code, null, null);
    }

    /**
     * 自定义返回
     *
     * @param code 状态码 状态码
     * @param msg 提示信息  提示信息
     * @return JsonRes对象，Json封装
     */
    public static JsonRes result(int code, String msg) {
        return new JsonRes(code, msg, null);
    }

    /**
     * 自定义返回
     *
     * @param code 状态码 状态码
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes result(int code, Object data) {
        return new JsonRes(code, null, data);
    }

    /**
     * 自定义返回
     *
     * @param code 状态码 状态码
     * @param msg 提示信息  提示信息
     * @param data 数据 返回数据
     * @return JsonRes对象，Json封装
     */
    public static JsonRes result(int code, String msg, Object data) {
        return new JsonRes(code, msg, data);
    }
    // endregion
}
