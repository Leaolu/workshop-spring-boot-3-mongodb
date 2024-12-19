package com.leaolu.workshopmongo.DTO;

import java.io.Serializable;

import com.leaolu.workshopmongo.domain.User;

//DTO = Data Transfer Object
//DTO of an User, having Id, name and email as it attributes

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	
	//Constructors
	public UserDTO(){
	}
	
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	//doesn't have hashcode or equals because the Class User already has them
}
