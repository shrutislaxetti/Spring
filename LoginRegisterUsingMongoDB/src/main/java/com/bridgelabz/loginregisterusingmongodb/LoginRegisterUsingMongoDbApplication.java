package com.bridgelabz.loginregisterusingmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.bridgelabz.*" })
public class LoginRegisterUsingMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginRegisterUsingMongoDbApplication.class, args);
	}
}
