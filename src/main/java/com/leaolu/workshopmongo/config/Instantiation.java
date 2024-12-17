package com.leaolu.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.leaolu.workshopmongo.DTO.AuthorDTO;
import com.leaolu.workshopmongo.DTO.CommentDTO;
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
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "partiu viagem", "vou viajar para sp, abracos", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("25/06/2019"), "estou triste", "vou parar de publicar aqui, valeu", new AuthorDTO(bob));
		
		CommentDTO c1 = new CommentDTO("Boa viagem!", sdf.parse("17/02/2019"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveita!", sdf.parse("09/07/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Melhoras!", sdf.parse("11/07/2019"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		
		userRepository.save(maria);
	}

}
