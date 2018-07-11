package com.bridgelabz.springbootrestlogin.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.bridgelabz.springbootrestlogin.model.User;
import com.bridgelabz.springbootrestlogin.repository.UserRepository;
import com.bridgelabz.springbootrestlogin.repository.UserRepositoryImplementation;

@Service("userService")
public class UserServiceImplementation implements UserService {

	@Override
	public User login(User user1) {
	
		UserRepository userRepository = new UserRepositoryImplementation();
		User user = userRepository.getUserByUserName(user1);
		String pass = user.getPassward();
		if (user != null) {
			if (user.getPassward() == pass) {

				return user;
			}
		}
		return null;
	}

	@Override
	public User saveUser(User user1) throws SQLException {

		UserRepository userRepository = new UserRepositoryImplementation();

		User user2 = userRepository.getUserByUserName(user1);
		
		if (user2 == null) {
			if(userRepository.saveUser(user1)) {
				return user1;
			}
			
		}
		return null;
	}
}
