package com.sofosofi.identsystemwechat.common.aop.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 拦截登陆的注解 注解依赖HttpServletRequset 对象
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginLogAop {

}
