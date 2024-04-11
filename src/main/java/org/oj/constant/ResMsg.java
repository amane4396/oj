package org.oj.constant;

/**
 * 接口返回信息
 * @author DH
 */
public class ResMsg {
    /**
     * 成功
     */
    public static final String SUCCESS = "操作成功";
    public static final String CREATE_SUCCESS = "数据添加成功";
    public static final String DELETE_SUCCESS = "数据删除成功";
    public static final String UPDATE_SUCCESS = "数据更新成功";
    public static final String GET_DATA_SUCCESS = "数据获取成功";
    public static final String RANK_SUCCESS = "调整排序成功";
    public static final String IMPORT_SUCCESS = "数据导入成功";
    public static final String EXPORT_SUCCESS = "数据导出成功";

    /**
     * 已知问题
     */
    public static final String BAD = "操作未能执行";
    public static final String UNAUTHORIZED = "";
    public static final String ACCESS_DENIED = "无操作权限";
    public static final String NOT_FOUND = "数据不存在";
    public static final String RANK_UP_DATA_IS_TOP = "已到最前位置";
    public static final String DELETE_INCOMPLETE = "删除操作未能完成";
    public static final String ALL_DELETE_DATA_NOT_FOUND = "需要删除的数据不存在";
    public static final String PARTIAL_DELETE_DATA_NOT_FOUND = "操作未全部执行，某些需要删除的数据不存在";
    public static final String ALL_DELETE_DATA_ASSOCIATED = "数据删除失败";
    public static final String PARTIAL_DELETE_DATA_ASSOCIATED = "操作未全部执行，某些";
    public static final String DELETE_DATA_NOT_FOUND_OR_ASSOCIATED = "操作未全部执行，某些需要删除的数据不存在，或";
    public static final String CREATE_CONFLICT = "数据已存在";
    public static final String UPDATE_CONFLICT = "数据已存在";

    /**
     * 未知错误，异常
     */
    public static final String FAIL = "操作发生错误";
    public static final String CREATE_FAIL = "数据添加发生错误";
    public static final String DELETE_FAIL = "数据删除发生错误";
    public static final String UPDATE_FAIL = "数据更新发生错误";
    public static final String GET_DATA_FAIL = "数据获取发生错误";
    public static final String RANK_FAIL = "调整排序发生错误";
    public static final String LOGIN_FAIL = "登录发生错误";
    public static final String IMPORT_FAIL = "数据导入发生错误";
    public static final String EXPORT_FAIL = "数据导出发生错误";

}
