package com.example.demo.repositories;

import com.example.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    public Optional<Post> findByDescription(String Description);
    public Post findPostByDescription(String Description);

    public Post findPostByImage(byte[] image);
}
