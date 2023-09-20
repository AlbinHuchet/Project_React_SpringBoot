package com.example.demo.repositories;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserByEmail(String email);
    public User findUserByUsername(String username);
    public User findUserByEmailAndPassword(String email, String password);
}
