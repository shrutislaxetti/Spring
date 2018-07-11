package com.bridgelabz.loginregisterusingmongodb.service;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bridgelabz.loginregisterusingmongodb.model.User;
import com.bridgelabz.loginregisterusingmongodb.repository.UserRepository;

    @Repository
	public class UserServiceImplementation implements UserService {

	    @Autowired
	    UserRepository repository;

	    public User login(User user) throws ClassNotFoundException, SQLException {
		Optional<User> userReturn;
	          
	            System.out.println("user found");
	            userReturn = repository.findById(user.getEmail());
	           
	       
	        if (userReturn.isPresent()) {
	            if (userReturn.get().getPassword().equals(user.getPassword())) {
	                System.out.println("pass match");
	               user.setUserName(userReturn.get().getUserName());
	                return user;
	           }

	        }
	       
			return null;
	    }
	 
		@Override
		public boolean saveUser(User user) throws SQLException, ClassNotFoundException {
			Optional<User> optional=repository.findById(user.getEmail());
		//	repository.existsById(user.getEmail())
			if(optional.isPresent()) {
				return false;
			}
			else {
				repository.save(user);
				return true;
			}
		}
		}




