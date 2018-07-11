package com.bridgelabz.springbootrestlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.bridgelabz.*"})
public class SpringBootRestApiExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiExampleApplication.class, args);
	}
}
