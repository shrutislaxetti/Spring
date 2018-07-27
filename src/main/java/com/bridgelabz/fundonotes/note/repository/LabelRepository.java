package com.bridgelabz.fundonotes.note.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bridgelabz.fundonotes.note.models.Label;

public interface LabelRepository extends MongoRepository<Label, String>{
	
    public List<Label> findAllByuserId(String userId);

	public boolean existsBylabelId(String labelId);

	public Optional<Label> findByLabelId(String labelId);

	public List<Label> findAllByLabelId(String labelId);

	public Optional<Label> findByLabelName(String labelName);

	public Optional<Label> findByLabelNameAndUserId(String labelName);

	
}
