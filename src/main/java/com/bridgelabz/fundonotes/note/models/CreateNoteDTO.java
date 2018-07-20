package com.bridgelabz.fundonotes.note.models;

public class CreateNoteDTO {

	private String noteId;
	
	private String title;

	private String description;

	public String getId() {
		return noteId;
	}

	public void setId(String id) {
		this.noteId = id;
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

	@Override
	public String toString() {
		return "CtreateNoteDTO [title=" + title + ", description=" + description + "]";
	}

}
