package com.bridgelabz.fundonotes.usermodule.controller;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundonotes.usermodule.exceptions.ActivationException;
import com.bridgelabz.fundonotes.usermodule.exceptions.FogetPasswordException;
import com.bridgelabz.fundonotes.usermodule.exceptions.RegistrationException;
import com.bridgelabz.fundonotes.usermodule.model.ForgetPasswordDTO;
import com.bridgelabz.fundonotes.usermodule.model.LoginDTO;
import com.bridgelabz.fundonotes.usermodule.model.RegistrationDTO;
import com.bridgelabz.fundonotes.usermodule.model.Response;
import com.bridgelabz.fundonotes.usermodule.service.UserService;

@RestController
@RequestMapping("/loginregister")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userservice;
	Response responseDTO = new Response();

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Response> login(@RequestBody LoginDTO loginDTO, HttpServletResponse resp)
			throws LoginException {

		String token = userservice.login(loginDTO);
		responseDTO.setMessage("Login Successfull");
		responseDTO.setStatus("201");
		resp.setHeader("token", token);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Response> register(@RequestBody RegistrationDTO registration)
			throws RegistrationException, MessagingException {

		userservice.register(registration);

		responseDTO.setMessage("Registration Successfull!!");
		responseDTO.setStatus("201");
		return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ResponseEntity<Response> activateUser(@RequestParam("token") String token)
			throws AddressException, MessagingException, ActivationException {

		userservice.activateAccount(token);
		responseDTO.setMessage("Account activated");
		responseDTO.setStatus("true");
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public ResponseEntity<Response> forgetpassword(@RequestParam("email") String email) throws MessagingException, FogetPasswordException {
		userservice.forgotPassword(email);
		responseDTO.setMessage("Password Reset Link Sent");
		responseDTO.setStatus("202");
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/setpassword", method = RequestMethod.PUT)
	public ResponseEntity<Response> setNewPassword(@RequestBody ForgetPasswordDTO forgetPasswordDTO,@RequestParam("token") String token) throws FogetPasswordException {
		userservice.setNewPssword(forgetPasswordDTO, token);
		responseDTO.setMessage("Resetting Password successfull");
		responseDTO.setStatus("201");
		return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);

	}

}
