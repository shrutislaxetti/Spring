package com.bridgelabz.fundonotes.note.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
@Document(indexName = "note", type = "Note")
//@Document(collection = "note")
public class Note {

	@Id
	private String noteId;
	private String title;
	private String description;
	private Date createdAt;
	private Date updatedAt;
	private String colour ="white";
	private Date remainder;
	private String userId;
	private boolean trash;
	private boolean Archive;
	private List<LabelDTO> labels;

	public boolean isArchive() {
		return Archive;
	}

	public void setArchive(boolean archive) {
		Archive = archive;
	}

	public boolean isPin() {
		return pin;
	}

	public void setPin(boolean pin) {
		this.pin = pin;
	}

	private boolean pin;

	public List<LabelDTO> getLabel() {
		return labels;
	}

	public void setLabel(List<LabelDTO> list) {
		this.labels = list;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
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

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<LabelDTO> getLabels() {
		return labels;
	}

	public void setLabels(List<LabelDTO> labels) {
		this.labels = labels;
	}

	public Date getRemainder() {
		return remainder;
	}

	public void setRemainder(Date date) {
		this.remainder = date;
	}

}
