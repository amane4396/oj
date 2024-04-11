package org.oj.annotation;

import java.lang.annotation.*;

/**
 * 权限注解
 *
 * @author DH
 * @create 2020-12-09
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    String value() default "";
}
