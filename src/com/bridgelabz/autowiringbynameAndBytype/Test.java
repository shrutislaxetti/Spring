package com.bridgelabz.autowiringexamples;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		 AbstractApplicationContext context = new ClassPathXmlApplicationContext("autowiring.xml");
		 
	        //autowire=byName 
	        Application application = (Application)context.getBean("app");
	        System.out.println("Application Details : "+application);
	}

}
