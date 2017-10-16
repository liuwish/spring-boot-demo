
package com.github.jartisan.springbootdemo.web.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.github.jartisan.parent.base.annotation.Security;
import com.github.jartisan.parent.base.consts.Const;
import com.github.jartisan.springbootdemo.configuration.SpringBeanUtil;
import com.github.jartisan.springbootdemo.model.authorization.TokenModel;
import com.github.jartisan.springbootdemo.service.authorization.TokenManager;
/***
 * 身份拦截器
 * @author ejb3
 *
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
	
	protected final Logger log = LogManager.getLogger(getClass());
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod){
			TokenManager tokenManager = SpringBeanUtil.getBeanByClass(TokenManager.class);
	        HandlerMethod handlerMethod = (HandlerMethod) handler;
	        Method method = handlerMethod.getMethod();
	        // 从 header 中得到 token
	        String authorization = request.getHeader(Const.AUTHORIZATION);
	        // 验证 token
	        TokenModel model = tokenManager.getToken (authorization);
	        if (tokenManager.checkToken (model)) {
	            // 如果 token 验证成功，将 token 对应的用户 id 存在 request 中，便于之后注入
	            request.setAttribute (Const.CURRENT_USER_ID, model.getUserId ());
	            return true;
	        }
	        // 如果验证 token 失败，并且方法注明了 Authorization，返回 401 错误
	        if (method.getAnnotation (Security.class) != null) {
	            response.setStatus (HttpServletResponse.SC_UNAUTHORIZED);
	            return false;
	        }
	      }
		return true;
	}
}
