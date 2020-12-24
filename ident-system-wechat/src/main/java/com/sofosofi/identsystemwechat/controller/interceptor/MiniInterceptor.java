package com.sofosofi.identsystemwechat.controller.interceptor;

import com.sofosofi.identsystemwechat.common.Constants;
import com.sofosofi.identsystemwechat.common.RedisOperator;
import com.sofosofi.identsystemwechat.common.protocol.SofoJSONResult;
import com.sofosofi.identsystemwechat.utils.JsonUtils;
import com.sofosofi.identsystemwechat.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

@Slf4j
public class MiniInterceptor implements HandlerInterceptor {

	@Autowired
	public RedisOperator redis;

	/**
	 * 拦截请求，在controller调用之前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {
		String userName = request.getHeader("headerUserName");
		String userToken = request.getHeader("headerUserToken");
		//如果不为空，判断userName 对应的key是否和redis中的数据一致
		if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(userToken)) {
			String uniqueToken = redis.get(String.format(Constants.USER_REDIS_SESSION, userName));
			if (StringUtils.isEmpty(uniqueToken)) {
				returnErrorResponse(response, SofoJSONResult.errorToken());
				return false;
			} else {
				if (!uniqueToken.equals(userToken)) {
					log.info("账号被挤出,userName:{}, userToken:{}, uniqueToken:{}", userName, userToken, uniqueToken);
					returnErrorResponse(response, SofoJSONResult.errorToken());
					return false;
				}
			}
		} else {
			returnErrorResponse(response, SofoJSONResult.errorToken());
			return false;
		}
		//校验通过，把用户信息写入threadLocal
		SessionUtils.setUserName(userName);
		return true;
	}
	
	public void returnErrorResponse(HttpServletResponse response, SofoJSONResult result)
			throws IOException, UnsupportedEncodingException {
		OutputStream out=null;
		try{
		    response.setCharacterEncoding("utf-8");
		    response.setContentType("text/json");
		    out = response.getOutputStream();
		    out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
		    out.flush();
		} finally{
		    if(out!=null){
		        out.close();
		    }
		}
	}
	
	/**
	 * 请求controller之后，渲染视图之前
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}
	
	/**
	 * 请求controller之后，视图渲染之后
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

}