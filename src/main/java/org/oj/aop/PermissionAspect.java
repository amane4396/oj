//package org.oj.aop;
//
//import org.oj.annotation.Permission;
//import org.oj.entity.User;
//import org.oj.util.JsonRes;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//
///**
// * 权限验证注解
// *
// * @author DH
// * @create 2020-12-11
// * @update 2022-06-20
// */
//@Aspect
//@Component
//public class PermissionAspect {
//
//    private final Logger log = LoggerFactory.getLogger("Permission");
//
//    @Resource
//    private HttpServletRequest request;
//
//    @Pointcut("@annotation(com.spms.annotation.Permission)")
//    private void permissionCut() {
//    }
//
//    @Around("permissionCut()")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        log.info("访问请求：" + request.getRequestURI());
//        //角色权限校验
//        MethodSignature methodSignature = (MethodSignature) point.getSignature();
//        Method method = methodSignature.getMethod();
//        if (method.isAnnotationPresent(Permission.class)) {
//            String token = request.getHeader("token");
//            //无token
//            if (!StringUtils.hasText(token)) {
//                log.info("请求返回：无token");
//                return JsonRes.unauthorized();
//            }
//            //用户不存在
//            User user = PermissionUtil.getCurrentUser(token);
//            if (user == null) {
//                log.info("请求返回：用户不存在");
//                return JsonRes.unauthorized();
//            }
//            //token过期
//            if (PermissionUtil.isTokenExpired(token)) {
//                log.info("请求返回：token过期");
//                return JsonRes.unauthorized();
//            }
//            //验证权限
//            Permission permission = method.getAnnotation(Permission.class);
//            if (!PermissionUtil.checkPermission(permission.value(), user)) {
//                log.info("请求返回：无权限");
//                return JsonRes.accessDenied();
//            }
//            //验证通过
//            request.setAttribute("token", token);
//            return point.proceed();
//        }
//        return null;
//    }
//}
