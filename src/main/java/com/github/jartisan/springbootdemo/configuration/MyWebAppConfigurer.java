package com.github.jartisan.springbootdemo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.github.jartisan.springbootdemo.web.interceptor.SecurityInterceptor;
/**
 * @ClassName: spring mvc interceptors
 * @Description:spring mvc interceptors
 * @author: wjl
 * @date: 2016年2月26日 上午11:39:24
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter{
	 @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        // 多个拦截器组成一个拦截器链
	        // addPathPatterns 用于添加拦截规则
	        // excludePathPatterns 用户排除拦截
	        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/**");
	        super.addInterceptors(registry);
	    }
}
