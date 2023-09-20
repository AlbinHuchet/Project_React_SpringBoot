package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(name="imagedata", length = 1000)
    private byte[] image;
    private String description;
    private String comment;
    private Integer likes;

    @ManyToOne
    private User user;

    public Post(Long id, byte[] image, String description, String comment, Integer likes, User user) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.comment = comment;
        this.likes = likes;
        this.user = user;
    }

    public Post(byte[] image, String description, String comment, Integer likes, User user) {
        this.image = image;
        this.description = description;
        this.comment = comment;
        this.likes = likes;
        this.user = user;
    }

    public Post(byte[] image, String description, String comment, Integer likes) {
        this.image = image;
        this.description = description;
        this.comment = comment;
        this.likes = likes;
    }

    public Post(byte[] image, String description) {
        this.image = image;
        this.description = description;
        this.likes = 0;
    }

    public Post(String description) {
        this.description = description;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                ", likes=" + likes +
                ", user=" + user +
                '}';
    }
}
