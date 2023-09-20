package com.example.demo.controllers;

import com.example.demo.entities.Post;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/allposts")
    public ResponseEntity<List<Post>> searchAllPosts(Model model) {
        return postService.findAllPosts(model);
    }
    @PostMapping("/createpost")
    public ResponseEntity<Post> createPost (@RequestBody Post post) {
        return postService.createPost(post);
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
