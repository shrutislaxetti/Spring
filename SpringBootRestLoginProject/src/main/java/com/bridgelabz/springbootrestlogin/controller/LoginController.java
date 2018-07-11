package com.bridgelabz.springbootrestlogin.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bridgelabz.springbootrestlogin.model.User;
import com.bridgelabz.springbootrestlogin.service.UserService;
import com.bridgelabz.springbootrestlogin.service.UserServiceImplementation;
import com.bridgelabz.springbootrestlogin.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class LoginController {

	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> loginUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {

		UserService userservice = new UserServiceImplementation();

		user = userservice.login(user);
		if (user != null) {
			return new ResponseEntity(("Welcome " + user.getUsername()), HttpStatus.FOUND);
		}
		return new ResponseEntity(new CustomErrorType("User with name " + user.getUsername()), HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody User user, UriComponentsBuilder ucbuilder) throws SQLException {

		UserService userservice = new UserServiceImplementation();

		User user1 = userservice.saveUser(user);
        System.out.println(user1);
		if (user1 != null) {
			return new ResponseEntity(("Registration successfull"), HttpStatus.FOUND);

		}

		return new ResponseEntity(
				new CustomErrorType(
						"User with name " + user.getUsername() + " found....Try to register with Differenet name"),
				HttpStatus.FOUND);
	}
}
