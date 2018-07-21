package com.bridgelabz.fundonotes.note.services;

import java.util.Date;
import java.util.List;

import com.bridgelabz.fundonotes.note.exceptions.DateException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNotFoundException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNullPointerException;
import com.bridgelabz.fundonotes.note.exceptions.UnauthorizedException;
import com.bridgelabz.fundonotes.note.models.CreateNoteDTO;
import com.bridgelabz.fundonotes.note.models.Note;
import com.bridgelabz.fundonotes.note.models.UpdateNoteDTO;
import com.bridgelabz.fundonotes.note.models.ViewNoteDTO;

public interface NoteService {

	public ViewNoteDTO createNote(CreateNoteDTO createNoteDTO,String token) throws NoteNotFoundException, NoteNullPointerException;

	public void updateNote(UpdateNoteDTO updateNoteDTO,String token, String noteId) throws UnauthorizedException, NoteNullPointerException, NoteNotFoundException;

	public void deleteNote(String token, String noteId) throws UnauthorizedException, NoteNotFoundException;

	public void deleteTrashNote(String token, String noteId) throws UnauthorizedException, NoteNotFoundException;

	public Note displayNote(String token, String noteId) throws UnauthorizedException, NoteNotFoundException;

	public void emptyTrash(String token) throws UnauthorizedException, NoteNotFoundException;

	public List<ViewNoteDTO> viewAllNote(String token) throws NoteNotFoundException;

	public void restorenote(String token, String noteId) throws UnauthorizedException, NoteNotFoundException;

	public void addRemainder(String token, String noteId, Date date) throws UnauthorizedException, NoteNotFoundException, DateException;

	public void removeRemainder(String token, String noteId, Date date) throws UnauthorizedException, NoteNotFoundException, DateException;

	
}
