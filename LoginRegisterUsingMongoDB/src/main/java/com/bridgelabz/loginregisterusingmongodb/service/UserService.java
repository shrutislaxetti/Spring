package com.bridgelabz.loginregisterusingmongodb.service;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;
import com.bridgelabz.loginregisterusingmongodb.model.User;
@Repository
public interface UserService {

	public User login(User user) throws ClassNotFoundException, SQLException;

	//public boolean checkEmail(User user) throws ClassNotFoundException, SQLException;

	public boolean saveUser(User user) throws SQLException, ClassNotFoundException;

}
