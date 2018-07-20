package com.bridgelabz.fundonotes.note.models;

public class Response {

	private String message;

	private int status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Response [message=" + message + ", status=" + status + "]";
	}

}
