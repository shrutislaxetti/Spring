package com.bridgelabz.fundonotes.note.services;

import com.bridgelabz.fundonotes.note.exceptions.NoteNotFoundException;
import com.bridgelabz.fundonotes.note.exceptions.UnauthorizedException;
import com.bridgelabz.fundonotes.note.models.CreateNoteDTO;
import com.bridgelabz.fundonotes.note.models.Note;
import com.bridgelabz.fundonotes.note.models.UpdateNoteDTO;

public interface NoteService {

	public Note createNote(CreateNoteDTO createNoteDTO,String token) throws NoteNotFoundException;

	public void updateNote(UpdateNoteDTO updateNoteDTO,String token, String noteId) throws UnauthorizedException, NoteNotFoundException;

	public void deleteNote(String token, String noteId) throws UnauthorizedException, NoteNotFoundException;

	public void deleteTrashNote(String token, String noteId) throws UnauthorizedException, NoteNotFoundException;

	public void emptyTrash(String token, String noteId);

}
