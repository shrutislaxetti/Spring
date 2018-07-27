package com.bridgelabz.fundonotes.note.services;

import java.util.Date;
import java.util.List;

import com.bridgelabz.fundonotes.note.exceptions.DateException;
import com.bridgelabz.fundonotes.note.exceptions.LabelException;
import com.bridgelabz.fundonotes.note.exceptions.NoteException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNotFoundException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNullPointerException;
import com.bridgelabz.fundonotes.note.exceptions.UnauthorizedException;
import com.bridgelabz.fundonotes.note.models.CreateNoteDTO;
import com.bridgelabz.fundonotes.note.models.Label;
import com.bridgelabz.fundonotes.note.models.Note;
import com.bridgelabz.fundonotes.note.models.UpdateNoteDTO;
import com.bridgelabz.fundonotes.note.models.ViewNoteDTO;

public interface NoteService {
	/**
	 * @param createNoteDTO
	 * @param userId
	 * @return
	 * @throws NoteNotFoundException
	 * @throws NoteNullPointerException
	 * @throws LabelException
	 */
	public ViewNoteDTO createNote(CreateNoteDTO createNoteDTO, String userId)
			throws NoteNotFoundException, NoteNullPointerException, LabelException;

	/**
	 * @param updateNoteDTO
	 * @param userId
	 * @param noteId
	 * @throws UnauthorizedException
	 * @throws NoteNullPointerException
	 * @throws NoteNotFoundException
	 */
	public void updateNote(UpdateNoteDTO updateNoteDTO, String userId, String noteId)
			throws UnauthorizedException, NoteNullPointerException, NoteNotFoundException;

	/**
	 * @param userId
	 * @param noteId
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 */
	public void deleteNote(String userId, String noteId) throws UnauthorizedException, NoteNotFoundException;

	/**
	 * @param userId
	 * @param noteId
	 * @return
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 */
	public Note displayNote(String userId, String noteId) throws UnauthorizedException, NoteNotFoundException;

	/**
	 * @param userId
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 */
	public void emptyTrash(String userId) throws UnauthorizedException, NoteNotFoundException;

	/**
	 * @param userId
	 * @return
	 */
	public List<ViewNoteDTO> viewAllNote(String userId);

	/**
	 * @param userId
	 * @param noteId
	 * @param date
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws DateException
	 */
	public void addRemainder(String userId, String noteId, Date date)
			throws UnauthorizedException, NoteNotFoundException, DateException;

	/**
	 * @param userId
	 * @param noteId
	 * @param date
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws DateException
	 */
	public void removeRemainder(String userId, String noteId, Date date)
			throws UnauthorizedException, NoteNotFoundException, DateException;

	/**
	 * @param userId
	 * @param noteId
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws NoteException
	 */
	public void addpinNote(String userId, String noteId)
			throws UnauthorizedException, NoteNotFoundException, NoteException;

	/**
	 * @param userId
	 * @param noteId
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws NoteException
	 */
	public void removepinNote(String userId, String noteId)
			throws UnauthorizedException, NoteNotFoundException, NoteException;

	/**
	 * @param userId
	 * @param noteId
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws NoteException
	 */
	public void addArchivePinNote(String userId, String noteId)
			throws UnauthorizedException, NoteNotFoundException, NoteException;

	/**
	 * @param userId
	 * @param noteId
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws NoteException
	 */
	public void removeArchivePinNote(String userId, String noteId)
			throws UnauthorizedException, NoteNotFoundException, NoteException;

	/**
	 * @param userId
	 * @return
	 */
	public List<ViewNoteDTO> viewAllArchiveNote(String userId);

	/**
	 * @param userId
	 * @param noteId
	 * @param isBoolean
	 * @throws UnauthorizedException
	 * @throws NoteException
	 * @throws NoteNotFoundException 
	 */
	public void deleteorrestore(String userId, String noteId, boolean isBoolean)
			throws UnauthorizedException, NoteException, NoteNotFoundException;

	/**
	 * @param labelName
	 * @param userId
	 * @throws LabelException
	 * @throws UnauthorizedException
	 */
	public void createLabel(String labelName, String userId) throws LabelException, UnauthorizedException;

	/**
	 * @param userId
	 * @return
	 * @throws UnauthorizedException
	 */
	public List<Label> viewLabels(String userId) throws UnauthorizedException;

	/**
	 * @param labelId
	 * @param userId
	 * @throws UnauthorizedException
	 * @throws LabelException
	 */
	public void deleteLabel(String labelId, String userId) throws UnauthorizedException, LabelException;

	/**
	 * @param userId
	 * @param noteId
	 * @param labelName
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws LabelException
	 */
	public void addLabeltoNote(String userId, String noteId, String labelName)
			throws UnauthorizedException, NoteNotFoundException, LabelException;

	/**
	 * @param userId
	 * @param noteId
	 * @param labelId
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws LabelException
	 */
	public void updateLabel(String userId, String noteId, String labelId)
			throws UnauthorizedException, NoteNotFoundException, LabelException;

	/**
	 * @param userId
	 * @param labelId
	 * @param noteId
	 * @throws NoteNotFoundException
	 * @throws UnauthorizedException
	 * @throws LabelException
	 */
	public void removeLabelFromNote(String userId, String labelId, String noteId)
			throws NoteNotFoundException, UnauthorizedException, LabelException;

}
