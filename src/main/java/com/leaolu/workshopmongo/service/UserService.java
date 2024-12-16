package com.leaolu.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leaolu.workshopmongo.DTO.UserDTO;
import com.leaolu.workshopmongo.domain.User;
import com.leaolu.workshopmongo.repositories.UserRepository;
import com.leaolu.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(String id){
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
		
	}
	
	public User update(User obj, String id) {
		User user = findById(id);
		updateData(obj, user);
		return repository.save(user);
	}
	
	public void updateData(User obj, User user) {
		user.setEmail(obj.getEmail());
		user.setName(obj.getName());
	}
	
	public User fromDTO(UserDTO objDto) {
		 return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
}
