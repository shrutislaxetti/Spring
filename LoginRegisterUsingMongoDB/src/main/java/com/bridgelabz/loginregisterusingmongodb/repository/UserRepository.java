package com.bridgelabz.loginregisterusingmongodb.repository;



import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;
import com.bridgelabz.loginregisterusingmongodb.model.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
	
	public Optional<User> findByUserName(String userName);
	public Optional<User> findByEmail(String email);
}
