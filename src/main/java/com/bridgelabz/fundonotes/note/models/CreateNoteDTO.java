package com.bridgelabz.fundonotes.note.models;

import java.util.Date;
import java.util.List;

public class CreateNoteDTO {

	private String title;

	private String description;

	private String colour = "white";

	private Date remainder;

	private List<Label> labellist;

	private boolean archive;

	private boolean pin;

	public boolean getArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public boolean getPin() {
		return pin;
	}

	public void setPin(boolean pin) {
		this.pin = pin;
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

	public void setRemainder(Date remaindme) {
		this.remainder = remaindme;
	}

	public List<Label> getLabellist() {
		return labellist;
	}

	public void setLabellist(List<Label> labellist) {
		this.labellist = labellist;
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
