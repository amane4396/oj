package org.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.oj.dto.LoginDto;
import org.oj.dto.UserInfoDto;
import org.oj.entity.User;
import org.oj.exception.ActiveException;
import org.oj.mapstruct.AccountConvert;
import org.oj.service.AccountService;
import org.oj.service.UserService;
import org.oj.util.PermissionUtil;
import org.oj.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 账户相关 服务实现类
 *
 * @author DH
 * @create 2022-06-14
 * @update 2022-06-20
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private UserService userService;

    @Resource
    private AccountService accountService;


    @Override
    public UserInfoDto login(LoginDto dto) throws Exception {
        // 验证
        if (StringUtils.isEmpty(dto.getMemId())) {
            throw new ActiveException("用户名不能为空");
        }
        if (StringUtils.isEmpty(dto.getPassword())) {
            throw new ActiveException("密码不能为空");
        }

        User user = userService.selectWithAssociation(new LambdaQueryWrapper<User>().eq(User::getMemberId, dto.getMemId()));

        // 用户名验证
        if (user == null) {
            throw new ActiveException("用户名或密码错误");
        }
        String hashedPwd = SecurityUtil.encrypt("MD5", dto.getPassword(), user.getSalt());
        // 密码验证
        if (!user.getPassword().equals(hashedPwd)) {
            throw new ActiveException("用户名或密码错误");
        }

        //验证成功，维护token
        String token = PermissionUtil.addToken(user);

        return AccountConvert.INSTANCE.map(user, token);
    }

    @Override
    public void logout() throws Exception {
        PermissionUtil.removeToken();
    }

}




