package com.bridgelabz.fundonotes.note.models;

import java.util.Date;

public class CreateNoteDTO {

	private String title;

	private String description;

	private String colour="white";

	private Date remainder;
	
	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Date getRemainder() {
		return remainder;
	}

	public void setRemainder(Date remaindme) {
		this.remainder = remaindme;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
