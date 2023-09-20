package com.example.demo.services;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> createUser (User user) {
        if (user.getUsername() != null && user.getEmail() != null && user.getPassword() != null) {
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
    public ResponseEntity<User> updateUser (User user) {
        User u = userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        if (u != null) {
            u.setUsername(user.getUsername());
            u.setImage(user.getImage());
            u.setPosts(user.getPosts());
            userRepository.save(u);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
    public ResponseEntity<List<User>> findAllUsers (Model model) {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retourne un code NO CONTENT si l'objet est vide.
        } else {
            return ResponseEntity.ok(users);
        }
    }

    public ResponseEntity<String> deleteUser (User user) {
        User u = userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        if (u != null) {
            userRepository.delete(u);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
}
