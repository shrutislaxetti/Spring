package com.bridgelabz.fundonotes.note.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundonotes.note.exceptions.NoteNotFoundException;
import com.bridgelabz.fundonotes.note.exceptions.UnauthorizedException;
import com.bridgelabz.fundonotes.note.models.CreateNoteDTO;
import com.bridgelabz.fundonotes.note.models.Note;
import com.bridgelabz.fundonotes.note.models.UpdateNoteDTO;
import com.bridgelabz.fundonotes.note.services.NoteService;
import com.bridgelabz.fundonotes.user.models.Response;

@RestController
public class NoteController {

	@Autowired
	NoteService noteService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Response> createNote(@RequestBody CreateNoteDTO  createnote, @RequestHeader String token) throws NoteNotFoundException {
		Note note = noteService.createNote(createnote,token);
		Response response = new Response();
		response.setMessage("Note Created Successfully!!");
		response.setStatus(201);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateNote(@RequestBody UpdateNoteDTO updateNote,@RequestHeader String token,@PathVariable String noteId) throws NoteNotFoundException, UnauthorizedException {
	
		noteService.updateNote(updateNote,token,noteId);
		Response response = new Response();
		response.setMessage("Note Updated Successfully!!");
		response.setStatus(202);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{noteId}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteNote(@RequestHeader String token,@PathVariable String noteId) throws NoteNotFoundException, UnauthorizedException {
	
		noteService.deleteNote(token,noteId);
		Response response = new Response();
		response.setMessage("Note sent to Trash!!");
		response.setStatus(202);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deletetrash/{noteId}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteTrashNote(@RequestHeader String token,@PathVariable String noteId) throws NoteNotFoundException, UnauthorizedException {
	
		noteService.deleteTrashNote(token,noteId);
		Response response = new Response();
		response.setMessage("Note Deleted from Trash!!");
		response.setStatus(202);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/emptytrash/{noteId}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteAllNote(@RequestHeader String token,@PathVariable String noteId) throws NoteNotFoundException, UnauthorizedException {
	
		noteService.emptyTrash(token,noteId);
		Response response = new Response();
		response.setMessage("Trash Emptied!!");
		response.setStatus(202);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
