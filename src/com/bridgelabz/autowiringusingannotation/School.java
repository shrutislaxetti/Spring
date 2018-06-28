package com.bridgelabz.autowiringusingannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

public class School {

	@Autowired
	//@Qualifier("student1")
	 private Student student;


	public School() {
	System.out.println("defalut constructor School");	
	}

	

	@Override
	public String toString() {
		return "School [student=" + student + "]";
	}



	public Student getStudent() {
		return student;
	}
   
	@Required
	public void setStudent(Student student) {
		this.student = student;
		System.out.println("student setttor");
	}


	}


