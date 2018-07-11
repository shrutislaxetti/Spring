package com.bridgelabz.springbootrest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.springbootrest.services.Topic;

@RestController
public class TopicController {

	@RequestMapping("/topics")
	public List<Topic> getTopics() {
	 List<Topic> ar=new ArrayList<>();
			 ar.add(new Topic("Spring","1","Spring desc"));
		return ar;
	  
  }
}
