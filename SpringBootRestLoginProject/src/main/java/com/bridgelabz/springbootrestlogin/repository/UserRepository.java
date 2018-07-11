package com.bridgelabz.springbootrestlogin.repository;

import java.sql.SQLException;

import com.bridgelabz.springbootrestlogin.model.User;

public interface UserRepository {

	public User getUserByUserName(User user);

	public boolean saveUser(User user) throws SQLException;

}
