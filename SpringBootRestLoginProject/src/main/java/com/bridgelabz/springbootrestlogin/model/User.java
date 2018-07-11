package com.bridgelabz.springbootrestlogin.model;

public class User {
	@Override
	public String toString() {
		return "User [ username=" + username + ", email=" + email + ", phone=" + phone + ", passward="
				+ passward + "]";
	}

	
	private String username;
	private String email;
	private String phone;
	private String passward;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassward() {
		return passward;
	}

	public void setPassward(String passward) {
		this.passward = passward;
	}

	  
}
