package com.bridgelabz.autowiringusingannotation;

public class Student {

	private String name;
	private int id;
	private String email;

	public Student() {
		super();
		System.out.println("student default constructor");
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", email=" + email + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
