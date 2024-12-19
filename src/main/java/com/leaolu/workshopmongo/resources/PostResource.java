package com.leaolu.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leaolu.workshopmongo.domain.Post;
import com.leaolu.workshopmongo.resources.util.URL;
import com.leaolu.workshopmongo.service.PostService;

//RestController, receive the requests and answer than as the system respond to the requests

//putting /posts after the localhost URL allows to do the requests
@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	//automatically connects the service to the class
	@Autowired
	private PostService service;
	
	//putting the Posts Id after the /users and requesting GET, returns 200 with the object assigned to that id as body or 404 if the id isn't found
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//put the title after the /posts and setting to the GET request, returns 200 if it exists with a list of Posts with this title as body, or 404 if it doesn't
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam String text){
		//the title in the URL is coded usually, so it has to be decoded right after the request is done
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	//put the /fullsearch after the /posts with the value of text, minDate and maxDate and it returns a list of post
	//that were done with this text on its title, body or comments, and was done between those two dates
	//if the values aren't written, the system will auto complete the text as an empty string, the minDate with the minimal date of the system
	//that is 1970-01-01 and the maxDate as the current date of the system that sent the request
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> 
	fullSearch(@RequestParam(defaultValue = "") String text, 
			@RequestParam(defaultValue = "") String minDate,
			@RequestParam(defaultValue = "") String maxDate){
		text = URL.decodeParam(text);
		//if the first date is empty, the second date is value used in the operation
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = service.fullSearch(text,min, max);
		return ResponseEntity.ok().body(list);
	}
	
}
