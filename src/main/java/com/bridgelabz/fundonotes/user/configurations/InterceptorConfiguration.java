package com.bridgelabz.fundonotes.user.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bridgelabz.fundonotes.note.interceptor.NoteInterceptor;
import com.bridgelabz.fundonotes.user.loggerinterceptor.LoggerInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer{
	
	@Autowired
	private LoggerInterceptor loggerInterceptor;
	@Autowired
	private NoteInterceptor NoteInterceptor;
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
       
		registry.addInterceptor( loggerInterceptor);
	    registry.addInterceptor( NoteInterceptor).addPathPatterns("/notes/**");
    }
	
}
