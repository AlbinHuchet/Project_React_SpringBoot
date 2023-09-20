package com.example.demo.controllers;

import com.example.demo.entities.Post;
import com.example.demo.repositories.PostRepository;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/allposts")
    public ResponseEntity<List<Post>> searchAllPosts(Model model) {
        return postService.findAllPosts(model);
    }
    @PostMapping("/createpost")
    public ResponseEntity<Post> createPost (@RequestBody Post post) {
        return postService.createPost(post);
    }
    @PostMapping(value = "/createpost02", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createPost(
            @RequestPart("image") MultipartFile image,
            @RequestPart("description") String description
    ) {
        try {
            if (image != null && !image.isEmpty() && description != null && !description.isEmpty()) {
                Post post = new Post(image, description);
                postRepository.save(post);
                return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid image or description");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Vous pouvez gérer l'exception de manière appropriée ici
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create post");
        }
    }

    @PostMapping("/updatepost")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {return postService.updatePost(post);}
    @PostMapping("/likepost")
    public ResponseEntity<Post> likePost(@RequestBody Post post) {return postService.likePost(post);}
    @PostMapping("/dislikepost")
    public ResponseEntity<Post> dislikePost(@RequestBody Post post) {return postService.dislikePost(post);}
    @PostMapping("/commentpost")
    public ResponseEntity<Post> commentPost(@RequestBody Post post) {return postService.comment(post);}
    @DeleteMapping("/deletepost")
    public ResponseEntity<String> deletePost(@RequestBody Post post) {return postService.deletePost(post);}
}
