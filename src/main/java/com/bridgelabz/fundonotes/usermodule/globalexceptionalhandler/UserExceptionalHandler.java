package com.bridgelabz.fundonotes.usermodule.globalexceptionalhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bridgelabz.fundonotes.usermodule.exceptions.ActivationException;
import com.bridgelabz.fundonotes.usermodule.exceptions.LoginException;
import com.bridgelabz.fundonotes.usermodule.exceptions.RegistrationException;
import com.bridgelabz.fundonotes.usermodule.exceptions.FogetPasswordException;
import com.bridgelabz.fundonotes.usermodule.model.Response;

@ControllerAdvice
public class UserExceptionalHandler extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public static final Logger logger = LoggerFactory.getLogger(UserExceptionalHandler.class);
    @Autowired  
	private Response response ;
      
	@ExceptionHandler(RegistrationException.class)
	public ResponseEntity<Response> handleRegistrationException(RegistrationException exception){
		
		logger.info("Error occured during registration"+exception.getMessage() ,exception);
		response.setMessage(exception.getMessage());
		response.setStatus("403");
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Response> handleLoginException(LoginException exception){
		
		logger.info("Error occured during Login"+exception.getMessage() ,exception);
		response.setMessage(exception.getMessage());
		response.setStatus("401");
		return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(ActivationException.class)
	public ResponseEntity<Response> ActivationException(ActivationException exception){
		
		logger.info("Error occured during Activation"+exception.getMessage() ,exception);
		response.setMessage(exception.getMessage());
		response.setStatus("11");
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FogetPasswordException.class)
	public ResponseEntity<Response> FogetPasswordException(FogetPasswordException exception){
		
		logger.info("Error occured during Activation"+exception.getMessage() ,exception);
		response.setMessage(exception.getMessage());
		response.setStatus("11");
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleOtherException(Exception exception){
		
		logger.info("Error occured "+exception.getMessage() ,exception);
		response.setMessage(exception.getMessage());
		response.setStatus("0");
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
