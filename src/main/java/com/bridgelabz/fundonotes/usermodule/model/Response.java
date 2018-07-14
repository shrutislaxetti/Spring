package com.bridgelabz.fundonotes.usermodule.model;


import org.springframework.stereotype.Component;

@Component
public class Response {
	
	private String message;
	private String status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Response() {
		super();
	}

}
