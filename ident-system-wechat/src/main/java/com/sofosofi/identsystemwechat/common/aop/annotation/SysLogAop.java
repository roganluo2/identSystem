package com.sofosofi.identsystemwechat.common.aop.annotation;

import com.sofosofi.identsystemwechat.common.BusinessTypeEnum;

import java.lang.annotation.*;

/**
 * 系统日志切面注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAop {

    String title() default "";

    BusinessTypeEnum businessTypeEnum() default BusinessTypeEnum.DEFAULT;
}
