package com.malitrans.transport.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utilisateur fromUser;

    @ManyToOne
    private Utilisateur toUser;

    private int rating;
    private String comment;
    private LocalDateTime createdAt = LocalDateTime.now();

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Utilisateur getFromUser() { return fromUser; }
    public void setFromUser(Utilisateur fromUser) { this.fromUser = fromUser; }

    public Utilisateur getToUser() { return toUser; }
    public void setToUser(Utilisateur toUser) { this.toUser = toUser; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
