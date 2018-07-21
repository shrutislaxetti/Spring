package com.bridgelabz.fundonotes.note.services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundonotes.note.exceptions.DateException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNotFoundException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNullPointerException;
import com.bridgelabz.fundonotes.note.exceptions.UnauthorizedException;
import com.bridgelabz.fundonotes.note.models.CreateNoteDTO;
import com.bridgelabz.fundonotes.note.models.Note;
import com.bridgelabz.fundonotes.note.models.UpdateNoteDTO;
import com.bridgelabz.fundonotes.note.models.ViewNoteDTO;
import com.bridgelabz.fundonotes.note.repository.NoteRepository;
import com.bridgelabz.fundonotes.note.util.NoteUtility;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository respositry;

	@Override
	public ViewNoteDTO createNote(CreateNoteDTO createNoteDTO, String token) throws NoteNullPointerException {

		NoteUtility.validateNote(createNoteDTO);
	    String id = NoteUtility.parseJWT(token);

		Note note = new Note();
		note.setTitle(createNoteDTO.getTitle());
		note.setDescription(createNoteDTO.getDescription());
		note.setColour(createNoteDTO.getColour());
		note.setRemainder(createNoteDTO.getRemaindme());
		note.setUserid(id);
		note.setCreatedAt(NoteUtility.createdAt());
		note.setUpdatedAt(NoteUtility.createdAt());

		respositry.save(note);

		ViewNoteDTO viewnote = new ViewNoteDTO();
		viewnote.setTitle(note.getTitle());
		viewnote.setDescription(note.getDescription());
		viewnote.setColour(note.getColour());
		viewnote.setRemainder(note.getRemainder());
		viewnote.setCreatedAt(note.getCreatedAt());
		viewnote.setUpdatedAt(note.getUpdatedAt());

		return viewnote;

	}

	@Override
	public void updateNote(UpdateNoteDTO updateNoteDTO, String token, String noteId)
			throws UnauthorizedException, NoteNullPointerException, NoteNotFoundException {

		String userId = NoteUtility.parseJWT(token);

		Optional<Note> optionalnote = respositry.findById(noteId);

		if (!optionalnote.isPresent()) {
			throw new NoteNotFoundException("Note Not Present");
		}

		if (!userId.equals(optionalnote.get().getUserid())) {

			throw new UnauthorizedException("Token Not Present");
		}

		Note note = optionalnote.get();
		note.setTitle(updateNoteDTO.getTitle());
		note.setDescription(updateNoteDTO.getDescription());
		note.setColour(updateNoteDTO.getColour());
		note.setUpdatedAt(new Date());
		respositry.save(note);
	}

	@Override
	public void deleteNote(String token, String noteId) throws UnauthorizedException, NoteNotFoundException {

		String userId = NoteUtility.parseJWT(token);

		Optional<Note> optionalnote = respositry.findById(noteId);

		if (!optionalnote.isPresent()) {
			throw new UnauthorizedException("Note not Found");
		}
		if (!userId.equals(optionalnote.get().getUserid())) {

			throw new NoteNotFoundException("Token Not Present");
		}

		Note note = optionalnote.get();
		note.setTrash(true);
		respositry.save(note);

	}

	@Override
	public void deleteTrashNote(String token, String noteId) throws UnauthorizedException, NoteNotFoundException {

		String userId = NoteUtility.parseJWT(token);

		Optional<Note> optionalnote = respositry.findById(noteId);

		if (!optionalnote.isPresent()) {
			throw new UnauthorizedException("User not Found");
		}
		if (!userId.equals(optionalnote.get().getUserid())) {

			throw new NoteNotFoundException("Note Not Present");
		}

		if (!optionalnote.get().isTrash()) {
			throw new NoteNotFoundException("Note is not present in trash");
		}
		respositry.deleteById(noteId);
	}

	@Override
	public void emptyTrash(String token) throws UnauthorizedException, NoteNotFoundException {

		String userId = NoteUtility.parseJWT(token);
		List<Note> list = respositry.findAllByUserid(userId);

		List<Note> notelist = new LinkedList<>();
		for (int i = 0; i < list.size(); i++) {
			notelist.add(list.get(i));

			if (notelist.get(i).isTrash()) {
				respositry.delete(notelist.get(i));
			}
		}

	}

	@Override
	public Note displayNote(String token, String noteId) throws UnauthorizedException, NoteNotFoundException {

		String userId = NoteUtility.parseJWT(token);
		Optional<Note> optinallist = respositry.findById(noteId);

		if (!optinallist.isPresent()) {
			throw new UnauthorizedException("User not found");
		}
		if (!userId.equals(optinallist.get().getUserid())) {

			throw new NoteNotFoundException("Note Not Present");
		}

		Note note = optinallist.get();

		return note;

	}

	public List<ViewNoteDTO> viewAllNote(String token) throws NoteNotFoundException {
		String userId = NoteUtility.parseJWT(token);

		List<Note> noteList = respositry.findAllByUserid(userId);

		List<ViewNoteDTO> viewNoteList = new LinkedList<>();

		for (int i = 0; i < noteList.size(); i++) {

			if (!noteList.get(i).isTrash()) {

				ViewNoteDTO viewNote = new ViewNoteDTO();
				Note note = noteList.get(i);
				viewNote.setTitle(note.getTitle());
				viewNote.setDescription(note.getDescription());
				viewNote.setCreatedAt(note.getCreatedAt());
				viewNote.setUpdatedAt(note.getUpdatedAt());
				viewNote.setRemainder(note.getRemainder());
				viewNoteList.add(viewNote);
			}
		}
		return viewNoteList;

	}

	@Override
	public void restorenote(String token, String noteId) throws UnauthorizedException, NoteNotFoundException {
		String userId = NoteUtility.parseJWT(token);
		Optional<Note> optionalnote = respositry.findById(noteId);
		if (!optionalnote.isPresent()) {
			throw new UnauthorizedException("User not found");
		}
		if (!userId.equals(optionalnote.get().getUserid())) {
			throw new NoteNotFoundException("Note not found");
		}
		Note note = optionalnote.get();
		note.setTrash(false);
		respositry.save(note);

	}

	@Override
	public void addRemainder(String token, String noteId, Date date)
			throws UnauthorizedException, NoteNotFoundException, DateException {
		NoteUtility.validateDate(date);
		String userId = NoteUtility.parseJWT(token);
		Optional<Note> optionalnote = respositry.findById(noteId);
		if (!optionalnote.isPresent()) {
			throw new UnauthorizedException("User not found");
		}
		if (!userId.equals(optionalnote.get().getUserid())) {
			throw new NoteNotFoundException("Note not found");
		}
		Note note = optionalnote.get();
		note.setRemainder(date);
		respositry.save(note);
	}

	@Override
	public void removeRemainder(String token, String noteId, Date date)
			throws UnauthorizedException, NoteNotFoundException, DateException {
		NoteUtility.validateDate(date);
		String userId = NoteUtility.parseJWT(token);
		Optional<Note> optionalnote = respositry.findById(noteId);
		if (!optionalnote.isPresent()) {
			throw new UnauthorizedException("User not found");
		}
		if (!userId.equals(optionalnote.get().getUserid())) {
			throw new NoteNotFoundException("Note not found");
		}
		Note note = optionalnote.get();
		note.setRemainder(null);
		respositry.save(note);
	}

}
