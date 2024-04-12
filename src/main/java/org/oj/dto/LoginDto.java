package org.oj.dto;

import lombok.Data;

/**
 * 登录数据
 *
 * @author XT
 * @create 2024-04-12
 */
@Data
public class LoginDto {
    /**
     * 用户名
     */
    private String memId;
    /**
     * 密码
     */
    private String password;
}
