package com.bridgelabz.fundonotes.note.models;

public class UpdateNoteDTO {

	private String title;

	private String description;

	private String colour;

	private String remaindme;

	public String getRemaindme() {
		return remaindme;
	}

	public void setRemaindme(String remaindme) {
		this.remaindme = remaindme;
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

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

}
