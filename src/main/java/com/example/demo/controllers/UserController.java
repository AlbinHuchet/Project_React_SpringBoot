package com.example.demo.controllers;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/allusers")
    public ResponseEntity<List<User>> searchAllUsers (Model model) {
        return userService.findAllUsers(model);
    }
    @PostMapping("/createuser")
    public ResponseEntity<User> createUser (@RequestBody User user) {
        return userService.createUser(user);
    }
    @PostMapping("/updateuser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {return userService.updateUser(user);}
    @DeleteMapping("/deleteuser")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {return userService.deleteUser(user);}
}
