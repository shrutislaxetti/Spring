package com.bridgelabz.prepostannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	 @Bean(name="book")	
		public Book book(){
			return new Book("Java");
		}
}
