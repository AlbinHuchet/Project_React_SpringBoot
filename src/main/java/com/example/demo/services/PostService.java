package com.example.demo.services;

import com.example.demo.entities.ImageUtils;
import com.example.demo.entities.Post;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepo;
    private final String FOLDER_PATH="C:\\Users\\ajc\\Desktop\\coursFormation\\projets\\projets_solos\\projet_09_20\\demo\\src\\main\\resources\\images\\";

    public ResponseEntity<Post> createPost (Post post) {
        if (post.getDescription() != null) {
            post.setLikes(0);
            postRepo.save(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
//    public String uploadFile(MultipartFile file) throws IOException {
//        Post post = new Post(ImageUtils.compressImage(file.getBytes()));
//        if (post != null){
//            postRepo.save(post);
//            return "file uploaded successfully";
//        } else {
//            return null;
//        }
//    };
//    public byte[] downloadFile(String description){
//        Optional<Post> post = postRepo.findByDescription(description);
//        byte[] images=ImageUtils.decompressImage(post.get().getImage());
//        return images;
//    }
public String uploadImageToFileSystem(MultipartFile file, String description) throws IOException {
    String filePath=FOLDER_PATH+file.getOriginalFilename();
    Post p = new Post(filePath, description);
    Post post=postRepo.save(p);

    file.transferTo(new File(filePath));

    if (p != null) {
        return "file uploaded successfully : " + filePath;
    }
    return null;
}

    public byte[] downloadImageFromFileSystem(String description) throws IOException {
        Optional<Post> fileData = postRepo.findByDescription(description);
        String filePath=fileData.get().getImage();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
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
    public ResponseEntity<Post> updatePostByImage (Post post, String description) {
        Post p = postRepo.findPostByImage(post.getImage());
        if (p.getImage() != null) {
            p.setDescription(description);
            postRepo.save(p);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
    public ResponseEntity<Post> likePost (Post post) {
        Post p = postRepo.findPostByDescription(post.getDescription());
        if (p.getDescription() != null) {
            p.setLikes(post.getLikes() +1);
            postRepo.save(p);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
    public ResponseEntity<Post> dislikePost (Post post) {
        Post p = postRepo.findPostByDescription(post.getDescription());
        if (p.getDescription() != null) {
            p.setLikes(post.getLikes() -1);
            postRepo.save(p);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }
    public ResponseEntity<Post> comment (Post post) {
        Post p = postRepo.findPostByDescription(post.getDescription());
        if (p.getDescription() != null) {
            p.setComment(post.getComment());
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
