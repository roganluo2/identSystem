package com.sofosofi.identsystemwechat.common.aop.aspect.sysoper;

import com.sofosofi.identsystemwechat.common.aop.annotation.SysLogAop;
import com.sofosofi.identsystemwechat.entity.SysOperLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysLogContext {

    private HttpServletRequest request;

    private Throwable e;

    private Method method;

    private Object result;

}
