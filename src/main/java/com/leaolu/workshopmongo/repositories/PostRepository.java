package com.leaolu.workshopmongo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.leaolu.workshopmongo.domain.Post;

//Interface that extends MongoRepository, that allows to use functions that change the data base directly, like saving a document or deleting it

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	//function that allows the user to put the title of a post and get all its data in return, ignoring if there is a case letter in the search
	List<Post> findByTitleContainingIgnoringCase(String text);
	
	//Query method that searches the title in the first parameter of the function and ignore case letters
	@Query("{ 'title': { $regex: ?0, $options: 'i'} }")
	List<Post> searchTitle(String text);
	
	//Query method that get the minimum date and maximal date by the second and third parameter and searches the text passed as the first parameter in the title, body or the text of every comment of the post
	//returning the entire post that have this text on its title, body or comments, and that was made between the minimum and maximum date
	@Query("{ $and: [ {date: {$gte: ?1} }, {date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i'} }, { 'body': { $regex: ?0, $options: 'i'} }, { 'comments.text': { $regex: ?0, $options: 'i'} } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	
}
