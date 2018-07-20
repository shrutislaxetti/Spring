package com.bridgelabz.fundonotes.note.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bridgelabz.fundonotes.note.models.Note;

public interface NoteRepository  extends MongoRepository<Note, String> {

	void save(Optional<Note> note);

}
