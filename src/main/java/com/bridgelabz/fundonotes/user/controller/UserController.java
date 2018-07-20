package com.bridgelabz.fundonotes.user.controller;

import javax.mail.MessagingException;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundonotes.user.exceptions.FogetPasswordException;
import com.bridgelabz.fundonotes.user.exceptions.RegistrationException;
import com.bridgelabz.fundonotes.user.exceptions.UserNotFoundException;
import com.bridgelabz.fundonotes.user.models.ForgetPasswordDTO;
import com.bridgelabz.fundonotes.user.models.LoginDTO;
import com.bridgelabz.fundonotes.user.models.RegistrationDTO;
import com.bridgelabz.fundonotes.user.models.Response;
import com.bridgelabz.fundonotes.user.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userservice;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Response> register(@RequestBody RegistrationDTO registration)
			throws RegistrationException, MessagingException {

		userservice.register(registration);
		Response responseDTO = new Response();
		responseDTO.setMessage("Registration Successfull!!");
		responseDTO.setStatus(201);
		return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Response> login(@RequestBody LoginDTO loginDTO, HttpServletResponse resp)
			throws LoginException {

		String token = userservice.login(loginDTO);
		Response responseDTO = new Response();
		responseDTO.setMessage("Login Successfull");
		responseDTO.setStatus(201);
		resp.setHeader("token", token);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ResponseEntity<Response> activateUser(@RequestParam("token") String token)
			throws UserNotFoundException, MessagingException {

		userservice.activateAccount(token);
		Response responseDTO = new Response();
		responseDTO.setMessage("Account activated");
		responseDTO.setStatus(201);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public ResponseEntity<Response> forgetpassword(@RequestParam("email") String email)
			throws MessagingException, FogetPasswordException {
		
		userservice.forgotPassword(email);
		Response responseDTO = new Response();
		responseDTO.setMessage("Password Reset Link Sent");
		responseDTO.setStatus(202);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/setpassword", method = RequestMethod.PUT)
	public ResponseEntity<Response> setNewPassword(@RequestBody ForgetPasswordDTO forgetPasswordDTO,
			@RequestParam("token") String token) throws FogetPasswordException {
		userservice.setNewPssword(forgetPasswordDTO, token);
		Response responseDTO = new Response();
		responseDTO.setMessage("Resetting Password successfull");
		responseDTO.setStatus(201);
		return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

	}
	
	

}
