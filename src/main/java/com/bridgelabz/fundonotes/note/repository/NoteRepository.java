package com.bridgelabz.fundonotes.note.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.bridgelabz.fundonotes.note.models.Note;

public interface NoteRepository  extends MongoRepository<Note, String> {


	public List<Note> findAllByUserid(String userId);

}
