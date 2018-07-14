package com.bridgelabz.fundonotes.usermodule.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RegistrationDTO {
	
	
	@Id
	private String email;
	private String userName;
	private String contactNum;
	private String password;
	private String confirmpassword;

	@Override
	public String toString() {
		return "RegistrationDTO [userName=" + userName + ", email=" + email + ", contactNum=" + contactNum
				+ ", password=" + password + ", confirmpassword=" + confirmpassword + "]";
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

}
