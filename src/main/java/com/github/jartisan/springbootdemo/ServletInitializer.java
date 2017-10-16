package com.github.jartisan.springbootdemo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/***
 * 
 * @author wjl
 * @date: 2016年2月26日 上午11:39:24
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootDemoApplication.class);
	}

}
