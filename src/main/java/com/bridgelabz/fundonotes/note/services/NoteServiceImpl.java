package com.bridgelabz.fundonotes.note.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.bridgelabz.fundonotes.note.exceptions.NoteNotFoundException;
import com.bridgelabz.fundonotes.note.exceptions.UnauthorizedException;
import com.bridgelabz.fundonotes.note.models.CreateNoteDTO;
import com.bridgelabz.fundonotes.note.models.Note;
import com.bridgelabz.fundonotes.note.models.UpdateNoteDTO;
import com.bridgelabz.fundonotes.note.repository.NoteRepository;
import com.bridgelabz.fundonotes.note.util.NoteUtility;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	NoteRepository respositry;

	@Override
	public Note createNote(CreateNoteDTO createNoteDTO, String token) throws NoteNotFoundException {

		NoteUtility.validateNote(createNoteDTO);
		String id = NoteUtility.parseJWT(token);
		Note note = new Note();
		note.setTitle(createNoteDTO.getTitle());
		note.setDescription(createNoteDTO.getDescription());
		note.setUserid(id);
		note.setCreatedAt(NoteUtility.createdAt());
		note.setUpdatedAt(NoteUtility.createdAt());
		respositry.save(note);
		return note;

	}

	@Override
	public void updateNote(UpdateNoteDTO updateNoteDTO, String token, String noteId)
			throws UnauthorizedException, NoteNotFoundException {

		NoteUtility.validateUpdatenote(updateNoteDTO);

		String userId = NoteUtility.parseJWT(token);

		Optional<Note> note = respositry.findById(noteId);

		if (!note.isPresent()) {
			throw new NoteNotFoundException("Note Not Present");
		}

		if (!userId.equals(note.get().getUserid())) {

			throw new UnauthorizedException("Token Not Present");
		}

		note.get().setTitle(updateNoteDTO.getTitle());
		note.get().setDescription(updateNoteDTO.getDescription());
		note.get().setUpdatedAt(NoteUtility.createdAt());
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
			throw new UnauthorizedException("Note not Found");
		}
		if (!userId.equals(optionalnote.get().getUserid())) {

			throw new NoteNotFoundException("Token Not Present");
		}

		Note note = optionalnote.get();
		note.setTrash(true);
		respositry.delete(note);
		

	}

	@Override
	public void emptyTrash(String token, String noteId) {
		

	}

}
