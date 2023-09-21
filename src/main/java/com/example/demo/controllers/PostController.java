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

import java.io.IOException;
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
//    @PostMapping("/createpost02")
//    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
//        String uploadImage = postService.uploadFile(file);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(uploadImage);
//    }
//    @GetMapping("/createpost02")
//    public ResponseEntity<?> downloadImage(@PathVariable String description){
//        byte[] imageData=postService.downloadFile(description);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(imageData);
//    }
@PostMapping("/createpost03")
public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image")MultipartFile file, @RequestParam("description")String description) throws IOException {
    String uploadImage = postService.uploadImageToFileSystem(file, description);
    return ResponseEntity.status(HttpStatus.OK)
            .body(uploadImage);
}

    @PostMapping("/createpost04")
    public ResponseEntity<?> downloadImageFromFileSystem(@RequestParam String description) throws IOException {
        byte[] imageData=postService.downloadImageFromFileSystem(description);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }
    @PostMapping("/updatepost")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {return postService.updatePost(post);}
    @PostMapping("/updatepost03")
    public ResponseEntity<Post> updatePostDescription(@RequestBody Post post, @RequestBody String description) {return postService.updatePostByImage(post, description);}
    @PostMapping("/likepost")
    public ResponseEntity<Post> likePost(@RequestBody Post post) {return postService.likePost(post);}
    @PostMapping("/dislikepost")
    public ResponseEntity<Post> dislikePost(@RequestBody Post post) {return postService.dislikePost(post);}
    @PostMapping("/commentpost")
    public ResponseEntity<Post> commentPost(@RequestBody Post post) {return postService.comment(post);}
    @DeleteMapping("/deletepost")
    public ResponseEntity<String> deletePost(@RequestBody Post post) {return postService.deletePost(post);}
}
