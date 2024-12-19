package com.leaolu.workshopmongo.service.exception;

//Customized exception for when a Object isn't found in the data base

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	//Constructor
	public ObjectNotFoundException(String msg) {
		super(msg);
	}

}
