package com.bridgelabz.springbootrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.bridgelabz.*" })
public class ToicControllerSpringBootApp {
	public static void main(String[] args) {

		SpringApplication.run(ToicControllerSpringBootApp.class, args);

	}
}
