package com.bridgelabz.fundonotes.note.controllers;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundonotes.note.exceptions.DateException;
import com.bridgelabz.fundonotes.note.exceptions.NoteException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNotFoundException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNullPointerException;
import com.bridgelabz.fundonotes.note.exceptions.UnauthorizedException;
import com.bridgelabz.fundonotes.note.models.CreateNoteDTO;
import com.bridgelabz.fundonotes.note.models.Note;
import com.bridgelabz.fundonotes.note.models.UpdateNoteDTO;
import com.bridgelabz.fundonotes.note.models.ViewNoteDTO;
import com.bridgelabz.fundonotes.note.services.NoteService;
import com.bridgelabz.fundonotes.note.exceptions.LabelException;
import com.bridgelabz.fundonotes.user.models.Response;

@RestController
@RequestMapping(value = "/notes")
public class NoteController {

	@Autowired
	private NoteService noteService;

	/**
	 * Api to create a new Note
	 * 
	 * @param req
	 * @param createnote
	 * @return
	 * @throws NoteNullPointerException
	 * @throws NoteNotFoundException
	 * @throws LabelException 
	 * @throws LabelException 
	 */

	@PostMapping(value = "/create")
	public ResponseEntity<ViewNoteDTO> createNote(HttpServletRequest req, @RequestBody CreateNoteDTO createnote)
			throws NoteNullPointerException, NoteNotFoundException, LabelException{

		String userId = (String)req.getAttribute("userId");
		ViewNoteDTO note = noteService.createNote(createnote, userId);
		return new ResponseEntity<>(note, HttpStatus.CREATED);

	}

	/**
	 * Api to Update a Note
	 * 
	 * @param updateNote
	 * @param token
	 * @param noteId
	 * @return
	 * @throws UnauthorizedException
	 * @throws NoteNullPointerException
	 * @throws NoteNotFoundException
	 */

	@PutMapping(value = "/update/{noteId}")
	public ResponseEntity<Response> updateNote(@RequestBody UpdateNoteDTO updateNote, HttpServletRequest req,
			@PathVariable String noteId) throws UnauthorizedException, NoteNullPointerException, NoteNotFoundException {
		String userId = (String) req.getAttribute("userId");
		noteService.updateNote(updateNote, userId, noteId);
		Response response = new Response();
		response.setMessage("Note Updated Successfully!!");
		response.setStatus(102);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * view a note
	 * 
	 * @param token
	 * @param noteId
	 * @return
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 */

	@PostMapping(value = "/view/{noteId}")
	public ResponseEntity<Note> displayNote(HttpServletRequest req, @PathVariable String noteId)
			throws UnauthorizedException, NoteNotFoundException {
		String userId = (String) req.getAttribute("userId");
		Note list = noteService.displayNote(userId, noteId);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	/**
	 * Api to View All Notes
	 * 
	 * @param token
	 * @return
	 * @throws NoteNotFoundException
	 */

	@GetMapping(value = "/viewAllNote")
	public ResponseEntity<List<ViewNoteDTO>> viewAllNote(HttpServletRequest req) {
		String userId = (String) req.getAttribute("userId");
		List<ViewNoteDTO> list = noteService.viewAllNote(userId);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	/**
	 * Api to Delete a Note by noteId
	 * 
	 * @param token
	 * @param noteId
	 * @return
	 * @throws NoteNotFoundException
	 * @throws UnauthorizedException
	 */

	@PutMapping(value = "/delete/{noteId}")
	public ResponseEntity<Response> deleteNote(HttpServletRequest req, @PathVariable String noteId)
			throws NoteNotFoundException, UnauthorizedException {

		String userId = (String) req.getAttribute("userId");
		noteService.deleteNote(userId, noteId);
		Response response = new Response();
		response.setMessage("Note sent to Trash!!");
		response.setStatus(401);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Api to Permanently Delete a note from Trash
	 * 
	 * @param token
	 * @param noteId
	 * @param isBoolean
	 * @return
	 * @throws UnauthorizedException
	 * @throws NoteException
	 * @throws NoteNotFoundException 
	 */

	@DeleteMapping(value = "/deleteforeverOrRestore/{noteId}")
	public ResponseEntity<Response> deleteTrashNote(HttpServletRequest req, @PathVariable String noteId,
			@RequestParam boolean isBoolean) throws UnauthorizedException, NoteException, NoteNotFoundException {

		String userId = (String) req.getAttribute("userId");
		noteService.deleteorrestore(userId, noteId, isBoolean);
		Response response = new Response();
		response.setMessage("Note Deleted from Trash!!");
		response.setStatus(402);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Api to Permanently Delete Or Restore Note
	 * 
	 * @param token
	 * @return
	 * @throws NoteNotFoundException
	 * @throws UnauthorizedException
	 */

	@DeleteMapping(value = "/emptytrash")
	public ResponseEntity<Response> deleteAllNote(HttpServletRequest req)
			throws NoteNotFoundException, UnauthorizedException {

		String userId = (String) req.getAttribute("userId");
		noteService.emptyTrash(userId);
		Response response = new Response();
		response.setMessage("Trash Emptied!!");
		response.setStatus(403);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Api to AddRemainder to Existing Note
	 * 
	 * @param token
	 * @param noteId
	 * @param date
	 * @return
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws DateException
	 */

	@PutMapping(value = "/addremainder/{noteId}")
	public ResponseEntity<Response> addRemainder(HttpServletRequest req, @PathVariable String noteId,
			@RequestBody Date date) throws UnauthorizedException, NoteNotFoundException, DateException {

		String userId = (String) req.getAttribute("userId");
		noteService.addRemainder(userId, noteId, date);
		Response response = new Response();
		response.setMessage("Remainder set successfully");
		response.setStatus(202);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	 * @param token
	 * @param noteId
	 * @return
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws DateException
	 * @throws NoteException
	 */

	@PutMapping(value = "/addpin/{noteId}")
	public ResponseEntity<Response> addPinNote(HttpServletRequest req, @PathVariable String noteId)
			throws UnauthorizedException, NoteNotFoundException, NoteException {

		String userId = (String) req.getAttribute("userId");
		
		noteService.addpinNote(userId, noteId);
		
		Response response = new Response();
		response.setMessage("Note Pinned successfully ");
		response.setStatus(203);
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	 * @param token
	 * @param noteId
	 * @return
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws DateException
	 * @throws NoteException
	 */

	@PutMapping(value = "/removepin/{noteId}")
	public ResponseEntity<Response> removePinNote(HttpServletRequest req, @PathVariable String noteId)
			throws UnauthorizedException, NoteNotFoundException, NoteException {

		String userId = (String) req.getAttribute("userId");
		
		noteService.removepinNote(userId, noteId);
		
		Response response = new Response();
		response.setMessage("Note Un-Pinned");
		response.setStatus(204);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	 * Api To Add Archive
	 * 
	 * @param token
	 * @param noteId
	 * @return
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws DateException
	 * @throws NoteException
	 */

	@PutMapping(value = "/addArchive/{noteId}")
	public ResponseEntity<Response> addArchiveNote(HttpServletRequest req, @PathVariable String noteId)
			throws UnauthorizedException, NoteNotFoundException, NoteException {

		String userId = (String) req.getAttribute("userId");
		
		noteService.addArchivePinNote(userId, noteId);
		
		Response response = new Response();
		response.setMessage("Note Archived!!");
		response.setStatus(205);
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	 * Api to Remove Archive
	 * 
	 * @param token
	 * @param noteId
	 * @return
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws DateException
	 * @throws NoteException
	 */

	@PutMapping(value = "/removeArchive/{noteId}")
	public ResponseEntity<Response> removeArchiveNote(HttpServletRequest req, @PathVariable String noteId)
			throws UnauthorizedException, NoteNotFoundException, NoteException {

		String userId = (String) req.getAttribute("userId");
		
		noteService.removeArchivePinNote(userId, noteId);
		Response response = new Response();
		response.setMessage("Note Un-Archived!!");
		response.setStatus(206);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	 * Api to View All Archives
	 * 
	 * @param token
	 * @return
	 */

	@GetMapping(value = "/viewAllArchive")
	public ResponseEntity<List<ViewNoteDTO>> viewAllArchive(HttpServletRequest req) {

		String userId = (String) req.getAttribute("userId");
		List<ViewNoteDTO> list = noteService.viewAllArchiveNote(userId);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	/**
	 * Api to Remove remainder
	 * 
	 * @param token
	 * @param noteId
	 * @param date
	 * @return
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws DateException
	 */

	@PutMapping(value = "/removeremainder/{noteId}")
	public ResponseEntity<Response> removeRemainder(HttpServletRequest req, @PathVariable String noteId,
			@RequestBody Date date) throws UnauthorizedException, NoteNotFoundException, DateException {

		String userId = (String) req.getAttribute("userId");
		noteService.removeRemainder(userId, noteId, date);
		Response response = new Response();
		response.setMessage("Remainder Reset successfully");
		response.setStatus(203);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	 * @param req
	 * @param noteId
	 * @param labelName
	 * @return
	 * @throws UnauthorizedException
	 * @throws NoteNotFoundException
	 * @throws LabelException
	 * @throws NoteException 
	 */
	@PutMapping(value = "/changeColour/{noteId}")
	public ResponseEntity<Response> changecolour(HttpServletRequest req, @PathVariable String noteId,
			@RequestParam String colour) throws NoteException, NoteNotFoundException, UnauthorizedException {

		String userid =(String) req.getAttribute("userId");
		noteService.addColourtoNote(userid, noteId, colour);
		Response response = new Response();
		response.setMessage("Colour Added successfully ");
		response.setStatus(205);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
}
