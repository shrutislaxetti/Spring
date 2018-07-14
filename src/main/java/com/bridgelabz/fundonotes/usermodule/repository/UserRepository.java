package com.bridgelabz.fundonotes.usermodule.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.bridgelabz.fundonotes.usermodule.model.User;

public interface UserRepository extends MongoRepository<User,String> {
	
	public Optional<User> findByUserName(String userName);
	public Optional<User> findByEmail(String email);
	public Optional<User> findByPassword(String password);
	
}
