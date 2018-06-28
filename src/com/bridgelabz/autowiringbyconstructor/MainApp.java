package com.bridgelabz.autowiringbyconstructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	
	public static void main(String[] args) {
	      ApplicationContext context = new ClassPathXmlApplicationContext("constructor.xml");
	      TextEditor te = (TextEditor) context.getBean("textEditor");
	    System.out.println(te.toString()); 
	  
	 }

}
