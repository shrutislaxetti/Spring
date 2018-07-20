package com.bridgelabz.fundonotes.user.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bridgelabz.fundonotes.user.loggerinterceptor.LoggerInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer{
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
       
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
    }
}
