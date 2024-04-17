package org.oj.dto.user;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 详情、编辑使用
 * 后端 -> 前端
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Data
public class UserForDetailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 用户类型
     */
    private Integer roleId;

    /**
     * 学号/教工号
     */
    private String memberId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 所属班级
     */
    private String classId;

    /**
     * 逻辑删除
     */
    private Integer deleted;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱号
     */
    private String email;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 院系id
     */
    private String departmentId;

    // region 枚举名称、外键名称===========================================================

    // endregion =============================================================================================
}
