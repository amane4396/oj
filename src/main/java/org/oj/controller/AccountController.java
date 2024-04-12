package org.oj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.oj.annotation.Permission;
import org.oj.dto.LoginDto;
import org.oj.dto.UserInfoDto;
import org.oj.service.AccountService;
import org.oj.util.JsonRes;
import lombok.extern.slf4j.Slf4j;
import org.oj.service.UserService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 账户相关
 *
 * @author DH
 * @create 2021-10-25
 * @update 2022-06-20
 */
@Slf4j
@RestController
@RequestMapping("/account")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
public class AccountController {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private AccountService accountService;

    @Resource
    private UserService userService;


    /**
     * 登录
     *
     * @param requestBody json
     * @return JsonRes
     */
    @PostMapping("/login")
    public JsonRes login(@RequestBody String requestBody) throws Exception {
        log.info(requestBody);
        LoginDto loginDto = objectMapper.readValue(requestBody, LoginDto.class);
        UserInfoDto accountDto = accountService.login(loginDto);
        return JsonRes.success(accountDto);
    }

    @RequestMapping(value = "/registerForTest",method = RequestMethod.POST)
    public JsonRes register(@RequestBody String requestBody) throws Exception{

        return JsonRes.success();
    }

    /**
     * 退出
     *
     * @return JsonRes
     */
    @PostMapping("/logout")
    public JsonRes logout() throws Exception {
        accountService.logout();
        return JsonRes.success();
    }
}
