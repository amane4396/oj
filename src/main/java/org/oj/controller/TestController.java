package org.oj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.oj.annotation.Permission;
import org.oj.dto.userType.UserTypeForUpdateDto;
import org.oj.entity.User;
import org.oj.entity.UserType;
import org.oj.global.Global;
import org.oj.service.UserService;
import org.oj.service.UserTypeService;
import org.oj.util.JsonRes;
import org.oj.util.PermissionUtil;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xt
 * @date 2024/4/12 16:02:52
 */
@Slf4j
@RestController
@RequestMapping("/test")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
public class TestController {

    @Resource
    private UserTypeService userTypeService;

    @Resource
    private UserService userService;


    @GetMapping("/search")
    public JsonRes update() throws Exception {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getRoleId, 1));
        return JsonRes.success(user.getName());
    }

    @GetMapping("/tokens")
    public JsonRes listForTokens(){
        return JsonRes.success(Global.tokens);
    }


}
