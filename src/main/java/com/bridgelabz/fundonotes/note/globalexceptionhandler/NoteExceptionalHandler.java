package com.bridgelabz.fundonotes.note.globalexceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bridgelabz.fundonotes.note.exceptions.DateException;
import com.bridgelabz.fundonotes.note.exceptions.LabelException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNotFoundException;
import com.bridgelabz.fundonotes.note.exceptions.NoteUpdationException;
import com.bridgelabz.fundonotes.note.exceptions.UnauthorizedException;
import com.bridgelabz.fundonotes.note.models.Response;

@ControllerAdvice
public class NoteExceptionalHandler {

	public static final Logger logger = LoggerFactory.getLogger(NoteExceptionalHandler.class);

	@ExceptionHandler(NoteNotFoundException.class)
	public ResponseEntity<Response> handleNoteCreationException(NoteNotFoundException exception) {

		logger.info("Error in Creatings Note.." + exception.getMessage(), exception);
		Response response = new Response();
		response.setMessage(exception.getMessage());
		response.setStatus(403);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoteUpdationException.class)
	public ResponseEntity<Response> handleNoteUpdationException(NoteUpdationException exception) {
		
		logger.info("Error in Updating Note.." + exception.getMessage(), exception);
		Response response = new Response();
		response.setMessage(exception.getMessage());
		response.setStatus(404);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<Response> handleUnauthorizedException(UnauthorizedException exception) {
		
		logger.error("Un_Authorized token.." + exception.getMessage(), exception);
		Response response = new Response();
		response.setMessage(exception.getMessage());
		response.setStatus(405);
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(DateException.class)
	public ResponseEntity<Response> handleDateException(DateException exception) {
		
		logger.error("Date is invalid" + exception.getMessage(), exception);
		Response response = new Response();
		response.setMessage(exception.getMessage());
		response.setStatus(405);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
   
	public ResponseEntity<Response> handleLabelException(LabelException exception) {
		
		logger.error("Label Already exists" + exception.getMessage(), exception);
		Response response = new Response();
		response.setMessage(exception.getMessage());
		response.setStatus(405);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
}
