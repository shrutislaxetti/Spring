package com.bridgelabz.fundonotes.note.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundonotes.note.exceptions.DateException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNotFoundException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNullPointerException;
import com.bridgelabz.fundonotes.note.exceptions.UnauthorizedException;
import com.bridgelabz.fundonotes.note.models.CreateNoteDTO;
import com.bridgelabz.fundonotes.note.models.Note;
import com.bridgelabz.fundonotes.note.models.UpdateNoteDTO;
import com.bridgelabz.fundonotes.note.models.ViewNoteDTO;
import com.bridgelabz.fundonotes.note.services.NoteService;
import com.bridgelabz.fundonotes.user.models.Response;

@RestController
public class NoteController {

	@Autowired
	private NoteService noteService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<ViewNoteDTO> createNote(@RequestBody CreateNoteDTO createnote, @RequestHeader String userId)
			throws NoteNullPointerException, NoteNotFoundException {
		ViewNoteDTO note = noteService.createNote(createnote, userId);
		return new ResponseEntity<>(note, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/update/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateNote(@RequestBody UpdateNoteDTO updateNote, @RequestHeader String token,
			@PathVariable String noteId) throws UnauthorizedException, NoteNullPointerException, NoteNotFoundException {

		noteService.updateNote(updateNote, token, noteId);
		Response response = new Response();
		response.setMessage("Note Updated Successfully!!");
		response.setStatus(102);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/view/{noteId}", method = RequestMethod.POST)
	public ResponseEntity<Note> displayNote(@RequestHeader String token, @PathVariable String noteId)
			throws UnauthorizedException, NoteNullPointerException, NoteNotFoundException {

		Note list = noteService.displayNote(token, noteId);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@RequestMapping(value = "/viewAll", method = RequestMethod.POST)
	public ResponseEntity<List<ViewNoteDTO>> viewAllNote(@RequestHeader String token)
			throws UnauthorizedException, NoteNullPointerException, NoteNotFoundException {

		List<ViewNoteDTO> list = noteService.viewAllNote(token);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@RequestMapping(value = "/delete/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<Response> deleteNote(@RequestHeader String token, @PathVariable String noteId)
			throws NoteNotFoundException, UnauthorizedException {

		noteService.deleteNote(token, noteId);
		Response response = new Response();
		response.setMessage("Note sent to Trash!!");
		response.setStatus(401);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletetrash/{noteId}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteTrashNote(@RequestHeader String token, @PathVariable String noteId)
			throws NoteNotFoundException, UnauthorizedException {

		noteService.deleteTrashNote(token, noteId);
		Response response = new Response();
		response.setMessage("Note Deleted from Trash!!");
		response.setStatus(402);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/emptytrash", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteAllNote(@RequestHeader String token)
			throws NoteNotFoundException, UnauthorizedException {

		noteService.emptyTrash(token);
		Response response = new Response();
		response.setMessage("Trash Emptied!!");
		response.setStatus(403);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/restore/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<Response> restoreNote(@RequestHeader String token, @PathVariable String noteId)
			throws UnauthorizedException, NoteNotFoundException {
		noteService.restorenote(token, noteId);
		Response response = new Response();
		response.setMessage("Note Restored successfully");
		response.setStatus(201);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/addremainder/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<Response> addRemainder(@RequestHeader String token, @PathVariable String noteId,
			@RequestBody Date date) throws UnauthorizedException, NoteNotFoundException, DateException {
		noteService.addRemainder(token, noteId, date);
		Response response = new Response();
		response.setMessage("Remainder set successfully");
		response.setStatus(202);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/removeremainder/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<Response> removeRemainder(@RequestHeader String token, @PathVariable String noteId,
			@RequestBody Date date) throws UnauthorizedException, NoteNotFoundException, DateException {
		noteService.removeRemainder(token, noteId, date);
		Response response = new Response();
		response.setMessage("Remainder Reset successfully");
		response.setStatus(203);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
