package com.leaolu.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leaolu.workshopmongo.DTO.UserDTO;
import com.leaolu.workshopmongo.domain.User;
import com.leaolu.workshopmongo.repositories.UserRepository;
import com.leaolu.workshopmongo.service.exception.ObjectNotFoundException;

//Service, that uses the Repository methods directly and is used by the RestController

@Service
public class UserService {
	
	//Automatically connects to the repository
	@Autowired
	private UserRepository repository;
	
	//try to find all the users in the data base using the repository method, that already returns a list of Users 
	public List<User> findAll(){
		return repository.findAll();
	}
	
	//try to find an User by it's Id, if succeeded it returns the object with this id, else it throws a ObjectNotFoundException
	public User findById(String id){
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	//insert an User returning the repository method insert, that already returns an User
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	//deletes an User by finding it by id, in case it finds, calls the deleteById method of the repository
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
		
	}
	
	//Find the User by the Id, uses an auxiliary method to update the data, and use the repository method save, that already returns the User saved
	public User update(User obj, String id) {
		User user = findById(id);
		updateData(obj, user);
		return repository.save(user);
	}
	
	//auxiliary method that gets two Users and set the second email and name with the firsts email and name
	public void updateData(User obj, User user) {
		user.setEmail(obj.getEmail());
		user.setName(obj.getName());
	}
	
	//turns a UserDTO into an User
	public User fromDTO(UserDTO objDto) {
		 return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
}
