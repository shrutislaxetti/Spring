package com.bridgelabz.springbootrestlogin.service;

import java.sql.SQLException;

import com.bridgelabz.springbootrestlogin.model.User;

public interface UserService {
	
	public User login(User user);

	public User saveUser(User user) throws SQLException;

}
