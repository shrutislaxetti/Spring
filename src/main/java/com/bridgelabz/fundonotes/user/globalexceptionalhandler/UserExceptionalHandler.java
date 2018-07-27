package com.bridgelabz.fundonotes.user.globalexceptionalhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bridgelabz.fundonotes.user.exceptions.LoginException;
import com.bridgelabz.fundonotes.user.exceptions.RegistrationException;
import com.bridgelabz.fundonotes.user.models.Response;
import com.bridgelabz.fundonotes.user.exceptions.UserNotFoundException;
import com.bridgelabz.fundonotes.user.exceptions.FogetPasswordException;

@ControllerAdvice
public class UserExceptionalHandler {
	
	public static final Logger logger = LoggerFactory.getLogger(UserExceptionalHandler.class);
   
	@ExceptionHandler(RegistrationException.class)
	public ResponseEntity<Response> handleRegistrationException(RegistrationException exception){
		
		logger.info("Error occured during registration"+exception.getMessage() ,exception);
		Response response = new Response();
		response.setMessage(exception.getMessage());
		response.setStatus(403);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Response> handleLoginException(LoginException exception){
		
		logger.info("Error occured during Login"+exception.getMessage() ,exception);
		Response response = new Response();
		response.setMessage(exception.getMessage());
		response.setStatus(401);
		return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Response> userNotFoundException(UserNotFoundException exception){
		
		logger.info("Error occured during Activation"+exception.getMessage() ,exception);
		Response response = new Response();
		response.setMessage(exception.getMessage());
		response.setStatus(11);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FogetPasswordException.class)
	public ResponseEntity<Response> fogetPasswordException(FogetPasswordException exception){
		
		logger.info("Error occured during Activation"+exception.getMessage() ,exception);
		Response response = new Response();
		response.setMessage(exception.getMessage());
		response.setStatus(11);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleOtherException(Exception exception){
		
		logger.error("Error occured "+exception.getMessage() ,exception);
		Response response = new Response();
		response.setMessage(exception.getMessage());
		response.setStatus(0);
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
