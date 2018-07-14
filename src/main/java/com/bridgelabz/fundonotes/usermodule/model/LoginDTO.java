package com.bridgelabz.fundonotes.usermodule.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class LoginDTO {
    
	private String email;
 
	private String password;

	@Override
	public String toString() {
		return "RegistrationDTO [ email=" + email + ", password=" + password + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
