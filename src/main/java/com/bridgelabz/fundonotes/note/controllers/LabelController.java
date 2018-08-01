package com.bridgelabz.fundonotes.note.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundonotes.note.exceptions.LabelException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNotFoundException;
import com.bridgelabz.fundonotes.note.exceptions.UnauthorizedException;
import com.bridgelabz.fundonotes.note.models.Label;
import com.bridgelabz.fundonotes.note.models.LabelDTO;
import com.bridgelabz.fundonotes.note.services.NoteService;
import com.bridgelabz.fundonotes.user.models.Response;

@RestController
@RequestMapping(value = "/label")
public class LabelController {
	
	@Autowired
	private NoteService noteService;

	/**
	 * Api to Create Label
	 * 
	 * @param req
	 * @param labelName
	 * @return
	 * @throws LabelException
	 * @throws UnauthorizedException
	 */

	@PostMapping(value = "/create/{labelName}")
	public ResponseEntity<LabelDTO> createLabel(HttpServletRequest req, @PathVariable String labelName)
			throws LabelException, UnauthorizedException {

		String userId = (String) req.getAttribute("userId");
		
		LabelDTO label=noteService.createLabel(labelName, userId);
		
		return new ResponseEntity<>(label, HttpStatus.CREATED);

	}

	/**
	 * Api to Add Label To Note
	 * 
	 * @param token
	 * @param noteId
	 * @param labelName
	 * @return
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws LabelException
	 */

	@PutMapping(value = "/add/{noteId}")
	public ResponseEntity<Response> addLabel(HttpServletRequest req, @PathVariable String noteId,
			@RequestParam String labelName) throws UnauthorizedException, NoteNotFoundException, LabelException {

		String userid =(String) req.getAttribute("userId");
		
		noteService.addLabeltoNote(userid, noteId, labelName);
		
		Response response = new Response();
		response.setMessage("Label Added successfully ");
		response.setStatus(203);
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	 * Api to Update Label
	 * 
	 * @param token
	 * @param noteId
	 * @param labelName
	 * @return
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws LabelException
	 */

	@PutMapping(value = "/update/{labelId}")
	public ResponseEntity<Response> updateLabel(HttpServletRequest req, @PathVariable String labelId,
			@RequestParam String labelName) throws UnauthorizedException, NoteNotFoundException, LabelException {

		String userId = (String) req.getAttribute("userId");
		
		noteService.updateLabel(userId,labelId,labelName);
		
		Response response = new Response();
		response.setMessage("Label Updated successfully ");
		response.setStatus(203);
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	/**
	 * Api to Delete Label
	 * 
	 * @param req
	 * @param labelId
	 * @return
	 * @throws UnauthorizedException
	 * @throws LabelException
	 */

	@DeleteMapping(value = "/deleteLabel")
	public ResponseEntity<Response> deleteLabel(HttpServletRequest req, @RequestParam String labelId)
			throws UnauthorizedException, LabelException {

		String userId = (String) req.getAttribute("userId");
		
		noteService.deleteLabel(userId, labelId);
		
		Response response = new Response();
		response.setMessage("Label Deleted Successfully");
		response.setStatus(101);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	/**
	 * Api to Remove label from the note
	 * @param req
	 * @param labelId
	 * @param noteId
	 * @return
	 * @throws NoteNotFoundException
	 * @throws UnauthorizedException
	 * @throws LabelException 
	 */
	
	@DeleteMapping(value = "/removeLabelFromNote/{noteId}")
	public ResponseEntity<Response> removeLabelFromNote(HttpServletRequest req, @RequestParam String labelId,@PathVariable String noteId)
			throws  NoteNotFoundException, UnauthorizedException, LabelException{

		String userId = (String) req.getAttribute("userId");
		
		noteService.removeLabelFromNote(userId, labelId,noteId);
		Response response = new Response();
		response.setMessage("Label Removed from Note");
		response.setStatus(101);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	/**
	 * Api to View Label
	 * 
	 * @param req
	 * @return
	 * @throws UnauthorizedException
	 */

	@GetMapping(value = "/viewLabel{userId}")
	public ResponseEntity<List<Label>> viewLabels(HttpServletRequest req) throws UnauthorizedException {

		String userId =(String) req.getAttribute("userId");
		List<Label> list = noteService.viewLabels(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);

	}
	
}
