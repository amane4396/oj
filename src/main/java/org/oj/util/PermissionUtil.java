package org.oj.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.oj.constant.ResCode;
import org.oj.entity.User;
import org.oj.exception.ActiveException;
import org.oj.global.Global;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 权限相关工具
 *
 * @author DH
 * @create 2020-12-11
 * @update 2022-06-20
 */
@Slf4j
@Component
public class PermissionUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 超时小时数
     */
    private static final int EXPIRATION = 5;

    // region token

    /**
     * 从访问请求头获取token
     *
     * @return token
     * @throws Exception 异常
     */
    public static String getToken() throws Exception {
        ServletRequestAttributes reqAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (reqAttr == null) {
            throw new ActiveException(ResCode.UNAUTHORIZED);
        }
        HttpServletRequest request = reqAttr.getRequest();
        String token = request.getHeader("token");
        if (token == null) {
            throw new ActiveException(ResCode.UNAUTHORIZED);
        }
        return token;
    }

    /**
     * 根据token获取当前登录用户
     *
     * @param token 令牌
     * @return User对象
     */
    public static User getCurrentUser(String token) throws Exception {
        if (Global.tokens.containsKey(token)) {
            return Global.tokens.get(token);
        } else {
            throw new ActiveException(ResCode.UNAUTHORIZED);
        }
    }

    /**
     * 根据请求头携带的token获取当前登录用户
     */
    public static User getCurrentUser() throws Exception {
        String token = getToken();
        if (Global.tokens.containsKey(token)) {
            return Global.tokens.get(token);
        } else {
            throw new ActiveException(ResCode.UNAUTHORIZED);
        }
    }

    /**
     * 获取当前登录用户Id
     */
    public static String getCurrentUserId() {
        try {
            User user = getCurrentUser();
            return user.getId();
        } catch (Exception e) {
            log.error("获取当前登录用户发生错误：{}", e.toString(), e);
            return null;
        }
    }

    /**
     * 生成token并维护
     */
    public static String addToken(User user) {
        String token = user.getId();
        User current = Global.tokens.get(token);
        if (current != null) {
            removeToken(token);
        }
        user.setPassword(null);
        user.setSalt(null);
        Global.tokens.put(token, user);
        return token;
    }

    /**
     * 移除token
     */
    public static void removeToken(String token) {
        Global.tokens.remove(token);
    }

    /**
     * 移除token
     */
    public static void removeToken() throws Exception {
        Global.tokens.remove(getToken());
    }

    private static LocalDateTime getExpiredTimeByNow() {
        return LocalDateTime.now().plusHours(EXPIRATION);
    }

}
