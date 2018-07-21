package com.bridgelabz.fundonotes.note.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bridgelabz.fundonotes.note.util.NoteUtility;

//@Component
public class NoteInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println(request.getHeader("token")+".................................");
		String userId = NoteUtility.parseJWT(request.getHeader("token"));
		request.setAttribute("userId", userId);
		System.out.println("inside interceptor");

		logger.info("Before handling the request");
		return true;
	}

}
