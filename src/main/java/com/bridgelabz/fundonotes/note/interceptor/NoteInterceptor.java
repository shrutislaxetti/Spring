package com.bridgelabz.fundonotes.note.interceptor;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bridgelabz.fundonotes.note.exceptions.UnauthorizedException;
import com.bridgelabz.fundonotes.note.util.NoteUtility;
import com.bridgelabz.fundonotes.user.models.User;
import com.bridgelabz.fundonotes.user.repositories.UserRepository;

@Component
public class NoteInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userrepository;
	
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String userId = NoteUtility.parseJWT(request.getHeader("token"));
		Optional<User> optional=userrepository.findById(userId);
		
		if(!optional.isPresent()) {
			throw new UnauthorizedException("Invalid Token");
		}
		request.setAttribute("userId", userId);
		logger.info("Before handling the request");
		return true;
	}

}
