package com.leaolu.workshopmongo.DTO;

import java.io.Serializable;

import com.leaolu.workshopmongo.domain.User;

//DTO = Data Transfer Object
//DTO of an author of a Post or comment, having id and name as attributes

public class AuthorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	
	//Constructors
	public AuthorDTO() {
	}
	
	public AuthorDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
	}
	
	
	//getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//doesn't have hashcode or equals because the class User already has themS
	
}
