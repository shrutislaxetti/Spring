package com.bridgelabz.fundonotes.note.models;

import java.util.Date;

public class ViewNoteDTO {

	private String title;

	private String description;

	private Date createdAt;

	private Date updatedAt;

	private String colour;

	private Date remainder;

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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Date getRemainder() {
		return remainder;
	}

	public void setRemainder(Date date) {
		this.remainder = date;
	}

	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	private boolean trash;

}
