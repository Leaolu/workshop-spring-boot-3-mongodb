package com.leaolu.workshopmongo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leaolu.workshopmongo.domain.Post;
import com.leaolu.workshopmongo.repositories.PostRepository;
import com.leaolu.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	public Post findById(String id){
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
}