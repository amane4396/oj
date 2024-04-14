package org.oj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.oj.annotation.Permission;
import org.oj.dto.submit.SubmitForCreateDto;
import org.oj.dto.userType.UserTypeForUpdateDto;
import org.oj.entity.User;
import org.oj.entity.UserType;
import org.oj.global.Global;
import org.oj.mapper.SubmitMapper;
import org.oj.service.UserService;
import org.oj.service.UserTypeService;
import org.oj.util.CustomClassLoader;
import org.oj.util.JsonRes;
import org.oj.util.PermissionUtil;
import org.oj.util.Task;
import org.oj.vo.Answer;
import org.oj.vo.Question;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Method;

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

    @Resource
    private ObjectMapper objectMapper;

    @GetMapping("/search")
    public JsonRes update() throws Exception {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getRoleId, 1));
        return JsonRes.success(user.getName());
    }

    @GetMapping("/tokens")
    public JsonRes listForTokens(){
        return JsonRes.success(Global.tokens);
    }

    @PostMapping("/run")
    public JsonRes runCode(@RequestBody String requestBody) throws Exception{
        SubmitForCreateDto dto = objectMapper.readValue(requestBody, SubmitForCreateDto.class);
        Question question = new Question();
        question.setCode(dto.getCode());
        question.setStdin("");
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Task task = new Task();
        task.compile(question);

        // 加载类获取方法反射
        Class<?> cls = customClassLoader.loadClassFromFile("./tmp/Solution.class");
        Method solution = cls.getMethod("solution", String.class);
        solution.invoke(cls, "XTxt1111");

        System.out.println(task.run());
        return JsonRes.success();
    }


}
