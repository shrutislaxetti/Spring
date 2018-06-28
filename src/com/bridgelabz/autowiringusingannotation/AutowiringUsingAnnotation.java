package com.bridgelabz.autowiringusingannotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowiringUsingAnnotation {

	public static void main(String[] args) {
	
	ApplicationContext context=new ClassPathXmlApplicationContext("annotation.xml");
	School school=(School) context.getBean("school");
	System.out.println(school.getStudent());
	System.out.println(school.toString());
 }

}
