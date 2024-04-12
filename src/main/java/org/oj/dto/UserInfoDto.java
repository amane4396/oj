package org.oj.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录后返回前端的信息
 *
 * @author DH
 * @create 2022-06-14
 */
@Data
public class UserInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 姓名
     */
    private String name;
    /**
     * 角色类型名称
     */
    private String roleTypeName;
    /**
     * 访问令牌
     */
    private String token;
}
