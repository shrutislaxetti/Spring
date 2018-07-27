package com.bridgelabz.fundonotes.note.models;

import java.util.Date;
import java.util.List;

public class ViewNoteDTO {
	
	private String id;
	private String title;
	private String description;
	private Date createdAt;
	private Date updatedAt;
	private String colour;
	private Date remainder;
	private boolean pin;
	private boolean archive;
	private List<LabelDTO> label;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<LabelDTO> getLable() {
		return label;
	}

	public void setLable(List<LabelDTO> list) {
		this.label = list;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean getPin() {
		return pin;
	}

	public void setPin(boolean pin) {
		this.pin = pin;
	}

	public boolean getArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
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
