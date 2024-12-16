package com.leaolu.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.leaolu.workshopmongo.DTO.AuthorDTO;
import com.leaolu.workshopmongo.domain.Post;
import com.leaolu.workshopmongo.domain.User;
import com.leaolu.workshopmongo.repositories.PostRepository;
import com.leaolu.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "partiu viagem", "vou viajar para sp, abracos", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("25/06/2019"), "estou triste", "vou parar de publicar aqui, valeu", new AuthorDTO(bob));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}
