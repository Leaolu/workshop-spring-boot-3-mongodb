package com.leaolu.workshopmongo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leaolu.workshopmongo.domain.Post;
import com.leaolu.workshopmongo.repositories.PostRepository;
import com.leaolu.workshopmongo.service.exception.ObjectNotFoundException;

//Service, that uses the Repository methods directly and is used by the RestController

@Service
public class PostService {
	
	//Automatically connects to the repository
	@Autowired
	private PostRepository repository;
	
	//try to find a post by its Id, if the Id exists, it returns the Post, if it doesn't, it throws a ObjectNotFoundException
	public Post findById(String id){
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	//try to fund a post by its title, return the repository methods that already returns a List of Posts
	public List<Post> findByTitle(String text) {
		return repository.searchTitle(text);
	}
	
	//turn the maxDate 1 day bigger because the date must be smaller or equal than the maxDate and 
	//calls for the repository method that already returns a list of Posts
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repository.fullSearch(text, minDate, maxDate);
	}
}
