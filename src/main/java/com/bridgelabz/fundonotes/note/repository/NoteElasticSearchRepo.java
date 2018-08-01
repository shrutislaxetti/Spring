package com.bridgelabz.fundonotes.note.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.bridgelabz.fundonotes.note.models.Note;


public interface NoteElasticSearchRepo extends ElasticsearchRepository<Note, String>{

	List<Note> findAllByUserId(String userId);

	
}
