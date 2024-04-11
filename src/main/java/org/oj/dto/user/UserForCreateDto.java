package org.oj.dto.user;

import org.oj.constant.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 添加使用
 * 前端 -> 后端
 *
 * @author XT
 * @create 2024-04-10
 * @update 2024-04-10
 */
@Data
public class UserForCreateDto implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 手机号
     */
    private String phone;

    /**
     * 邮箱号
     */
    private String email;
}
