package com.leaolu.workshopmongo.DTO;

import java.io.Serializable;
import java.util.Date;

//DTO = Data Transfer Object
//DTO of a Comment of a Post, with text, date and author as attributes

public class CommentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String text;
	private Date date;
	private AuthorDTO author;
	
	//Constructors
	public CommentDTO() {
	}

	public CommentDTO(String text, Date date, AuthorDTO author) {
		this.text = text;
		this.date = date;
		this.author = author;
	}
	
	//getters and setters
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
	
	//Doesn't have hashcode or equals because the class Post already has them
}
