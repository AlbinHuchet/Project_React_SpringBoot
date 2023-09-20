package com.example.demo;

import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjetReactSpringBootApplication {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetReactSpringBootApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner() {
//		return new CommandLineRunner() {
//
//			@Override
//			public void run(String... args) throws Exception {
//				String username = "Al";
//				String email = "Alcapone";
//				String mdp = "blabla";
//				if (userRepository.findUserByEmailAndPassword(email, mdp) == null) {
//					User user = new User(username, email, mdp);
//					userRepository.save(user);
//				}
//			}
//		};
//}
}
