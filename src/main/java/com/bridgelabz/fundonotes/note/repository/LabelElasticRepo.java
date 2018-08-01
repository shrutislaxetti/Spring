package com.bridgelabz.fundonotes.note.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bridgelabz.fundonotes.note.models.Label;

public interface LabelElasticRepo extends ElasticsearchRepository<Label, String> {

	Optional<Label> findByLabelName(String labelName);

	List<Label> findAllByuserId(String userId);

	Optional<Label> findByLabelId(String labelId);

}
