package com.bridgelabz.fundonotes.note.util;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import com.bridgelabz.fundonotes.note.exceptions.NoteNotFoundException;
import com.bridgelabz.fundonotes.note.exceptions.UnauthorizedException;
import com.bridgelabz.fundonotes.note.models.CreateNoteDTO;
import com.bridgelabz.fundonotes.note.models.UpdateNoteDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class NoteUtility {

	public static void validateNote(CreateNoteDTO noteDTO) throws NoteNotFoundException {
		if (noteDTO.getTitle() == null || noteDTO.getTitle().isEmpty()) {
			throw new NoteNotFoundException("Failed to Create Note ..Fileds should not be empty");
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
		Date date = new Date();
		return date;
	}

	public static void validateUpdatenote(UpdateNoteDTO updateNoteDTO)
			throws NoteNotFoundException, UnauthorizedException {

		if (updateNoteDTO.getTitle() == null
				|| updateNoteDTO.getTitle().isEmpty() && updateNoteDTO.getDescription() == null
				|| updateNoteDTO.getDescription().isEmpty()) {
			throw new NoteNotFoundException("Failed to Create Note ..Fileds should not be empty");
		}

	}

}
