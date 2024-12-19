package com.leaolu.workshopmongo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leaolu.workshopmongo.DTO.UserDTO;
import com.leaolu.workshopmongo.domain.Post;
import com.leaolu.workshopmongo.domain.User;
import com.leaolu.workshopmongo.service.UserService;

//RestController, receive the requests and answer than as the system respond to the requests

//putting /users after the localhost URL allows to do the requests
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	//Automatically connects to the service to the class
	@Autowired
	private UserService service;
	
	//the GET request uses DTO so the list of all posts assigned aren't returned when the GET is to return just the user
	
	//GET request that return a list of UserDTO as body followed with a 200 response
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listdto = list.stream().map(x -> new UserDTO(x)).toList();
		return ResponseEntity.ok().body(listdto);
	}
	
	//putting the Users Id after the /users and requesting GET, returns 200 with the object assigned to that id as body or 404 if the id isn't found
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	//POST request to add a User to the data base, it receives a userDTO in the body of the request, turn it into a User, insert it into the data base
	//and return 202, or, created
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO){
		User obj = service.fromDTO(userDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//put the Users Id after the /users and the DELETE request, it calls the delete method from service and returns 204, or, no content
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//put the Users Id after /posts and the PUT request, gets a UserDTO from the body of the request, turns it into a User, set its Id, calls the update method
	//and return a 200, or okay, and the object updated in its body
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@RequestBody UserDTO userDTO, @PathVariable String id){
		User obj = service.fromDTO(userDTO);
		obj.setId(id);
		service.update(obj, id);
		return ResponseEntity.ok().body(obj);
	}
	
	//put the Users id and the /posts after the /users so it can return the list of posts assigned to the User with that Id, 
	//returning 200, or ok, and the list of Posts
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
}
