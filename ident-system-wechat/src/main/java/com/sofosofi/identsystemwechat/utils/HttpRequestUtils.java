package com.sofosofi.identsystemwechat.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * httpServletRequest
 */
public final class HttpRequestUtils {

    /**
     * 获取参数中列表中的request对象
     * @return
     */
    public static HttpServletRequest paramHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        return Optional.of(sra).map(ServletRequestAttributes::getRequest).orElse(null);
    }

}
