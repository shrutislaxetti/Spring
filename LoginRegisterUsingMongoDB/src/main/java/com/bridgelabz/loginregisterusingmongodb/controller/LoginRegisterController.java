package com.bridgelabz.loginregisterusingmongodb.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.loginregisterusingmongodb.model.User;
import com.bridgelabz.loginregisterusingmongodb.service.UserService;
import com.bridgelabz.loginregisterusingmongodb.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class LoginRegisterController {

	public static final Logger logger = LoggerFactory.getLogger(LoginRegisterController.class);
	
	@Autowired
	UserService userservice;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> loginUser(@RequestBody User user)
			throws ClassNotFoundException, SQLException {
		
		logger.info("Logging User : {}", user);
		User user1=userservice.login(user);
		0//System.out.println(user.getUserName());
			if (user1 == null) {
				return new ResponseEntity(("Welcome " + user.getEmail()), HttpStatus.FOUND);
			}
			return new ResponseEntity(new CustomErrorType("User not found "), HttpStatus.NOT_FOUND);
			
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	
	public ResponseEntity<String> saveUser(@RequestBody User user)
			throws SQLException, ClassNotFoundException {
      
		logger.info("Register user : {}", user);
		
		boolean registered = userservice.saveUser(user);
		if(!registered) {
			logger.error("User with email {} already present.", user.getEmail());
			return new ResponseEntity("User with email " + user.getEmail()
            + " already present", HttpStatus.CONFLICT);
		}
		logger.info("User registered with : {}", user.getEmail());
		String message = "Successfully registered";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
