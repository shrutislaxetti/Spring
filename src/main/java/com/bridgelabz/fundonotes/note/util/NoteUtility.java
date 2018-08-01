package com.bridgelabz.fundonotes.note.util;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import com.bridgelabz.fundonotes.note.exceptions.DateException;
import com.bridgelabz.fundonotes.note.exceptions.NoteNullPointerException;
import com.bridgelabz.fundonotes.note.models.CreateNoteDTO;
import com.bridgelabz.fundonotes.note.exceptions.LabelException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class NoteUtility {

	private NoteUtility() {
		super();
		
	}

	public static void validateNote(CreateNoteDTO noteDTO) throws NoteNullPointerException {

		if (noteDTO.getTitle() == null || noteDTO.getTitle().trim().length()==0 && noteDTO.getDescription() == null
				|| noteDTO.getDescription().trim().length()==0 && noteDTO.getColour() == null || noteDTO.getColour().trim().length()==0) {
			throw new NoteNullPointerException("Failed to Create Note ..Fileds should not be empty");
		}
	}

	public static String tokenGenerator(String id) {
		String key = "shruti";
		long nowMillis = System.currentTimeMillis() + (20 * 60 * 60 * 1000);
		Date now = new Date(nowMillis);
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(id).signWith(SignatureAlgorithm.HS256,
				key);

		return builder.compact();
	}

	public static String parseJWT(String jwt) {
		String key = "shruti";
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(jwt)
				.getBody();
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		return claims.getId();

	}

	public static Date createdAt() {
		
		return new Date();
	}

	public static void validateDate(Date date) throws DateException {

        if(date.before(new Date())){
            throw new DateException("The date is older than current day");
        } else {
            System.out.println("The date is future day");
        }
		
	}

	public static void validateLabel(String labelName) throws LabelException {
		if(labelName ==null || labelName.isEmpty()) {
			throw new LabelException("LabelName should not be empty");
		}
		
	}

}
