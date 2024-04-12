package org.oj.service;

import org.oj.dto.LoginDto;
import org.oj.dto.UserInfoDto;

/**
 * @author DH
 * @create 2022-06-14
 * @update 2022-06-20
 */
public interface AccountService {

    /**
     * 登录
     *
     * @param dto 登录信息
     * @return User
     * @throws Exception 异常
     */
    UserInfoDto login(LoginDto dto) throws Exception;

    /**
     * 退出
     *
     * @throws Exception 异常
     */
    void logout() throws Exception;

}
