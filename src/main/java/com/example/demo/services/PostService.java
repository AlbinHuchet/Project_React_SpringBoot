package com.example.demo.services;

import com.example.demo.entities.Post;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepo;

    public ResponseEntity<Post> createPost (Post post) {
        if (post.getImage() != null && post.getDescription() != null) {
            post.setLikes(0);
            postRepo.save(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
    public ResponseEntity<Post> updatePost (Post post) {
        Post p = postRepo.findPostByDescription(post.getDescription());
        if (p.getImage() != null && p.getDescription() != null) {
           p.setImage(post.getImage());
           p.setDescription(post.getDescription());
            postRepo.save(p);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
    public ResponseEntity<List<Post>> findAllPosts (Model model) {
        List<Post> posts = postRepo.findAll();
        if (posts.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retourne un code NO CONTENT si l'objet est vide.
        } else {
            return ResponseEntity.ok(posts);
        }
    }

    public ResponseEntity<String> deletePost (Post post) {
        Post p = postRepo.findPostByDescription(post.getDescription());
        if (p != null) {
        postRepo.delete(p);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
}