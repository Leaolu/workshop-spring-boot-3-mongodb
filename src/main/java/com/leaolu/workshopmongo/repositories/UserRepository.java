package com.leaolu.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.leaolu.workshopmongo.domain.User;

//Interface that extends MongoRepository, that allows to use functions that change the data base directly, like saving a document or deleting it

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
