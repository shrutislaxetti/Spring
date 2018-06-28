package com.bridgelabz.autowiringexamples;

public class ApplicationUser {
	
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("settor ....");
	}

	@Override
	public String toString() {
		return "ApplicationUser [name=" + name + "]";
	}

}
